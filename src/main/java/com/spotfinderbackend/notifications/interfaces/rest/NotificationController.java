package com.spotfinderbackend.notifications.interfaces.rest;

import com.spotfinderbackend.notifications.domain.model.commands.CreateNotificationCommand;
import com.spotfinderbackend.notifications.domain.model.queries.GetNotificationsByUserIdQuery;
import com.spotfinderbackend.notifications.domain.services.NotificationCommandService;
import com.spotfinderbackend.notifications.domain.services.NotificationQueryService;
import com.spotfinderbackend.notifications.interfaces.rest.resources.CreateNotificationResource;
import com.spotfinderbackend.notifications.interfaces.rest.resources.NotificationResource;
import com.spotfinderbackend.notifications.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.spotfinderbackend.notifications.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationCommandService commandService;
    private final NotificationQueryService queryService;

    public NotificationController(
            NotificationCommandService commandService,
            NotificationQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // TS49 → ENVIAR NOTIFICACIÓN
    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(
            @RequestBody CreateNotificationResource resource
    ) {

        CreateNotificationCommand command =
                CreateNotificationCommandFromResourceAssembler.toCommand(resource);

        Long notificationId = commandService.handle(command);

        var notifications = queryService.handle(
                new GetNotificationsByUserIdQuery(resource.userId())
        );

        var notification = notifications.stream()
                .filter(n -> n.getId().equals(notificationId))
                .findFirst()
                .orElseThrow();

        return ResponseEntity.ok(
                NotificationResourceFromEntityAssembler.toResource(notification)
        );
    }

    // TS50 → LISTAR NOTIFICACIONES POR USUARIO
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResource>> getByUser(
            @PathVariable Long userId
    ) {

        var query = new GetNotificationsByUserIdQuery(userId);

        var notifications = queryService.handle(query);

        var response = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response);
    }
}