package com.spotfinderbackend.accesscontrol.infrastructure.alpr;

import com.fasterxml.jackson.databind.JsonNode;
import com.spotfinderbackend.accesscontrol.domain.model.valueobjects.LicensePlate;
import com.spotfinderbackend.accesscontrol.domain.services.PlateRecognitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Optional;

/**
 * Plate Recognizer (Snapshot Cloud) API client.
 * <p>
 * Sends the image as {@code multipart/form-data} to {@code apiUrl} using the
 * header {@code Authorization: Token <apiToken>} and parses the JSON response
 * ({@code results[0].plate} / {@code results[0].score}).
 * <p>
 * When no token is configured (or the call fails) the client falls back to a
 * deterministic stub plate — controlled by {@code platerecognizer.stub-fallback}
 * — so the barrier flow can still be exercised end-to-end without credentials.
 */
@Service
public class PlateRecognizerClient implements PlateRecognitionService {

    private static final Logger LOG = LoggerFactory.getLogger(PlateRecognizerClient.class);
    private static final LicensePlate STUB_PLATE = new LicensePlate("ABC-123", 0.9);

    private final String apiUrl;
    private final String apiToken;
    private final String regions;
    private final boolean stubFallback;
    private final RestClient restClient;

    public PlateRecognizerClient(
            @Value("${platerecognizer.api-url:https://api.platerecognizer.com/v1/plate-reader/}") String apiUrl,
            @Value("${platerecognizer.api-token:}") String apiToken,
            @Value("${platerecognizer.regions:}") String regions,
            @Value("${platerecognizer.stub-fallback:true}") boolean stubFallback,
            RestClient.Builder restClientBuilder
    ) {
        this.apiUrl = apiUrl;
        this.apiToken = apiToken;
        this.regions = regions;
        this.stubFallback = stubFallback;
        this.restClient = restClientBuilder.build();
    }

    @Override
    public Optional<LicensePlate> recognizePlate(byte[] imageData) {
        if (imageData == null || imageData.length == 0) return Optional.empty();

        if (apiToken == null || apiToken.isBlank()) {
            if (stubFallback) {
                LOG.warn("Plate Recognizer token not configured; returning stub plate {}", STUB_PLATE.plateText());
                return Optional.of(STUB_PLATE);
            }
            LOG.error("Plate Recognizer token not configured and stub fallback disabled");
            return Optional.empty();
        }

        try {
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("upload", new ByteArrayResource(imageData) {
                @Override
                public String getFilename() {
                    return "plate.jpg";
                }
            });
            if (regions != null && !regions.isBlank()) {
                for (String region : regions.split(",")) {
                    if (!region.isBlank()) form.add("regions", region.trim());
                }
            }

            JsonNode response = restClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Token " + apiToken)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(form)
                    .retrieve()
                    .body(JsonNode.class);

            return parseResponse(response);
        } catch (Exception ex) {
            LOG.error("Plate Recognizer call failed: {}", ex.getMessage());
            return stubFallback ? Optional.of(STUB_PLATE) : Optional.empty();
        }
    }

    private Optional<LicensePlate> parseResponse(JsonNode response) {
        if (response == null) return Optional.empty();
        JsonNode results = response.get("results");
        if (results == null || !results.isArray() || results.isEmpty()) {
            LOG.info("Plate Recognizer returned no plate for the submitted image");
            return Optional.empty();
        }

        JsonNode top = results.get(0);
        String plate = top.path("plate").asText("");
        double score = top.path("score").asDouble(0.0);
        if (plate.isBlank()) return Optional.empty();

        try {
            LicensePlate licensePlate = new LicensePlate(plate, score);
            LOG.info("Plate Recognizer detected {} (score={})", licensePlate.plateText(), score);
            return Optional.of(licensePlate);
        } catch (RuntimeException ex) {
            // Foreign / malformed plates rejected by the Peruvian-format domain rule.
            LOG.warn("Recognized plate '{}' rejected by domain rules: {}", plate, ex.getMessage());
            return Optional.empty();
        }
    }
}
