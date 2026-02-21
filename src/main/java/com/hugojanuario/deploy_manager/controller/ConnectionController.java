package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.client.dto.ClientCreateRequest;
import com.hugojanuario.deploy_manager.domain.connection.dto.ConnectionCreateRequest;
import com.hugojanuario.deploy_manager.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/connection")
public class ConnectionController {
    private final ConnectionService connectionService;


    @PostMapping
    public ResponseEntity createConnection(@RequestBody ConnectionCreateRequest connectionCreateRequest, UriComponentsBuilder uriBuilder) {
        var newConnection = connectionService.createConnection(connectionCreateRequest);
        return ResponseEntity.ok().body(newConnection);
    }
}
