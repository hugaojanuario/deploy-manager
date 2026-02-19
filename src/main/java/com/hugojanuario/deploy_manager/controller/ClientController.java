package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.client.dto.ClientCreateRequest;
import com.hugojanuario.deploy_manager.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity findAllClient(Pageable page){
        var findClient = clientService.findAllClients(page);
        return ResponseEntity.ok(findClient);
    }
}
