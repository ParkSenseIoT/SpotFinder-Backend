package com.spotfinderbackend.payments.interfaces.rest;

import com.spotfinderbackend.payments.domain.model.queries.GetPaymentByIdQuery;
import com.spotfinderbackend.payments.domain.model.queries.GetPaymentHistoryQuery;
import com.spotfinderbackend.payments.domain.services.PaymentCommandService;
import com.spotfinderbackend.payments.domain.services.PaymentQueryService;
import com.spotfinderbackend.payments.interfaces.rest.resources.CreatePaymentResource;
import com.spotfinderbackend.payments.interfaces.rest.resources.PaymentResource;
import com.spotfinderbackend.payments.interfaces.rest.resources.ProcessCulqiPaymentResource;
import com.spotfinderbackend.payments.interfaces.rest.resources.ProcessYapePaymentResource;
import com.spotfinderbackend.payments.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.spotfinderbackend.payments.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import com.spotfinderbackend.payments.interfaces.rest.transform.ProcessCulqiPaymentCommandFromResourceAssembler;
import com.spotfinderbackend.payments.interfaces.rest.transform.ProcessYapePaymentCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentCommandService commandService;
    private final PaymentQueryService queryService;

    public PaymentController(
            PaymentCommandService commandService,
            PaymentQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // TS44 → Create Payment
    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(
            @RequestBody CreatePaymentResource resource
    ) {

        var command = CreatePaymentCommandFromResourceAssembler.toCommand(resource);

        Long paymentId = commandService.handle(command);

        var payment = queryService.handle(new GetPaymentByIdQuery(paymentId));

        var response = PaymentResourceFromEntityAssembler.toResource(payment);

        return ResponseEntity.status(201).body(response);
    }

    // TS45 → Get Payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResource> getPaymentById(
            @PathVariable Long paymentId
    ) {

        var payment = queryService.handle(new GetPaymentByIdQuery(paymentId));

        return ResponseEntity.ok(
                PaymentResourceFromEntityAssembler.toResource(payment)
        );
    }

    // TS46 → Culqi mock
    @PostMapping("/{paymentId}/culqi")
    public ResponseEntity<PaymentResource> processCulqi(
            @PathVariable Long paymentId,
            @RequestBody ProcessCulqiPaymentResource resource
    ) {

        var command = ProcessCulqiPaymentCommandFromResourceAssembler
                .toCommand(paymentId, resource);

        commandService.handle(command);

        var payment = queryService.handle(new GetPaymentByIdQuery(paymentId));

        return ResponseEntity.ok(
                PaymentResourceFromEntityAssembler.toResource(payment)
        );
    }

    // TS47 → Yape mock
    @PostMapping("/{paymentId}/yape")
    public ResponseEntity<PaymentResource> processYape(
            @PathVariable Long paymentId,
            @RequestBody ProcessYapePaymentResource resource
    ) {

        var command = ProcessYapePaymentCommandFromResourceAssembler
                .toCommand(paymentId, resource);

        commandService.handle(command);

        var payment = queryService.handle(new GetPaymentByIdQuery(paymentId));

        return ResponseEntity.ok(
                PaymentResourceFromEntityAssembler.toResource(payment)
        );
    }

    // TS48 → History
    @GetMapping("/history/{sessionId}")
    public ResponseEntity<List<PaymentResource>> getHistory(
            @PathVariable Long sessionId
    ) {

        var payments = queryService.handle(new GetPaymentHistoryQuery(sessionId));

        var response = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response);
    }
}