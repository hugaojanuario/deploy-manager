package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.connection.dto.ConnectionCreateRequest;
import com.hugojanuario.deploy_manager.domain.connection.dto.ConnectionUpdateRequest;
import com.hugojanuario.deploy_manager.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/connection")
public class ConnectionController {
    private final ConnectionService connectionService;


    @PostMapping
    public ResponseEntity createConnection(@RequestBody ConnectionCreateRequest connectionCreateRequest) {
        var newConnection = connectionService.createConnection(connectionCreateRequest);

        return ResponseEntity.ok().body(newConnection);
    }

    @GetMapping("/{id}")
    public ResponseEntity findByIdConnection(@PathVariable UUID id){
        var find = connectionService.findByIdConnection(id);

        return ResponseEntity.ok(find);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateConnection (@PathVariable UUID id, @RequestBody ConnectionUpdateRequest connectionUpdateRequest){
        var update = connectionService.updateConnection(id, connectionUpdateRequest);

        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteConnection (@PathVariable UUID id){
        connectionService.deleteConnection(id);

        return ResponseEntity.noContent().build();
    }

}
