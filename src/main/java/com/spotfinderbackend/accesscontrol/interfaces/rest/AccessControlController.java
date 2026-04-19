package com.spotfinderbackend.accesscontrol.interfaces.rest;

import com.spotfinderbackend.accesscontrol.domain.services.AccessControlCommandService;
import com.spotfinderbackend.accesscontrol.interfaces.rest.resources.*;
import com.spotfinderbackend.accesscontrol.interfaces.rest.transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/access")
public class AccessControlController {

    private final AccessControlCommandService commandService;

    public AccessControlController(AccessControlCommandService commandService) {
        this.commandService = commandService;
    }

    // TS35 → ENTRY
    @PostMapping("/entries")
    public ResponseEntity<Void> registerEntry(
            @RequestBody RegisterEntryResource resource
    ) {

        var command = RegisterEntryCommandFromResourceAssembler.toCommand(resource);

        commandService.handle(command);

        return ResponseEntity.status(201).build(); // 201 Created
    }

    // TS36 → EXIT
    @PostMapping("/exits")
    public ResponseEntity<Void> registerExit(
            @RequestBody RegisterExitResource resource
    ) {

        var command = RegisterExitCommandFromResourceAssembler.toCommand(resource);

        commandService.handle(command);

        return ResponseEntity.ok().build(); // 200 OK
    }

    // TS37 → ALPR
    @PostMapping("/alpr")
    public ResponseEntity<AlprResultResource> processAlpr(
            @RequestBody ProcessAlprResource resource
    ) {

        var command = ProcessAlprCommandFromResourceAssembler.toCommand(resource);

        var result = commandService.handle(command);

        var response = AlprResultResourceFromEntityAssembler.toResource(result);

        return ResponseEntity.ok(response); // 200 OK
    }
}