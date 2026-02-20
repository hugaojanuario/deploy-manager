package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.client.dto.ClientCreateRequest;
import com.hugojanuario.deploy_manager.domain.client.dto.ClientUpdateRequest;
import com.hugojanuario.deploy_manager.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity createdClient (@RequestBody @Valid ClientCreateRequest clientCreateRequest){
        var newClient = clientService.createClient(clientCreateRequest);
        return ResponseEntity.ok().body(newClient);
    }

    @GetMapping
    public ResponseEntity findAllClient(@PageableDefault(size = 2) Pageable page){
        var findClient = clientService.findAllClients(page);
        return ResponseEntity.ok(findClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity findByIdClient(@PathVariable UUID id){
        var findById = clientService.findByIdClient(id);
        return ResponseEntity.ok(findById);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable UUID id, @RequestBody @Valid ClientUpdateRequest clientUpdateRequest){
        var upClient = clientService.updateClient(id, clientUpdateRequest);
        return ResponseEntity.ok(upClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClientById(@PathVariable UUID id){
        clientService.deleteCLient(id);
        return ResponseEntity.noContent().build();
    }
}
