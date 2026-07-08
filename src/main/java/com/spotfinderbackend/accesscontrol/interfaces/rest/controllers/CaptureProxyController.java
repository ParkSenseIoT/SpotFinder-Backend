package com.spotfinderbackend.accesscontrol.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

/**
 * Reverse proxy so the mobile app can drive the ESP32-CAM on demand
 * ("Verificar placa") without talking to the Edge Gateway directly. The app hits
 * the backend (:8080) as usual and the backend forwards to the edge (:5000).
 *
 * <p>Flow: app POST /capture/start -> edge turns the capture gate on -> the CAM
 * (which polls the edge) shoots a photo -> edge relays it to /access/entries
 * (ALPR) -> on a recognized plate the edge stores it and turns the gate off. The
 * app polls GET /capture until {@code recognized} is true, shows the plate and
 * refreshes. Idle = the CAM shoots nothing, so zero ALPR calls / quota.
 */
@RestController
@RequestMapping(value = "/api/v1/access/capture", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Access Control", description = "On-demand plate capture (proxy to the Edge Gateway)")
public class CaptureProxyController {

    private static final Logger log = LoggerFactory.getLogger(CaptureProxyController.class);

    private final RestClient edge;

    public CaptureProxyController(
            @Value("${spotfinder.edge-url:http://127.0.0.1:5000}") String edgeUrl) {
        this.edge = RestClient.builder().baseUrl(edgeUrl).build();
    }

    @Operation(summary = "Start on-demand capture (the app pressed 'Verificar placa')")
    @PostMapping("/start")
    public ResponseEntity<String> start() {
        return forwardPost("/api/v1/access/capture/start");
    }

    @Operation(summary = "Cancel on-demand capture")
    @PostMapping("/stop")
    public ResponseEntity<String> stop() {
        return forwardPost("/api/v1/access/capture/stop");
    }

    @Operation(summary = "Poll capture state: {capture, recognized, plate}")
    @GetMapping
    public ResponseEntity<String> status() {
        try {
            String body = edge.get().uri("/api/v1/access/capture").retrieve().body(String.class);
            return ResponseEntity.ok(body);
        } catch (Exception ex) {
            log.warn("Edge capture status unreachable: {}", ex.getMessage());
            return edgeDown();
        }
    }

    private ResponseEntity<String> forwardPost(String path) {
        try {
            String body = edge.post().uri(path).retrieve().body(String.class);
            return ResponseEntity.ok(body);
        } catch (Exception ex) {
            log.warn("Edge capture proxy failed ({}): {}", path, ex.getMessage());
            return edgeDown();
        }
    }

    private ResponseEntity<String> edgeDown() {
        return ResponseEntity.status(503)
                .body("{\"error\":\"edge unreachable\",\"capture\":false,\"recognized\":false,\"plate\":null}");
    }
}
