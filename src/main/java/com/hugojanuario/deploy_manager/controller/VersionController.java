package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.version.dto.VersionCreateRequest;
import com.hugojanuario.deploy_manager.domain.version.dto.VersionUpdateRequest;
import com.hugojanuario.deploy_manager.service.VersionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/version")
@RequiredArgsConstructor
public class VersionController {

    private final VersionService versionService;

    @PostMapping
    public ResponseEntity createdVersion (@RequestBody @Valid VersionCreateRequest versionCreateRequest, UriComponentsBuilder uriBuilder){
        var newVersion = versionService.createVersion(versionCreateRequest);

        var uri = uriBuilder.path("/apí/version/{id}").buildAndExpand(newVersion.id()).toUri();

        return ResponseEntity.created(uri).body(newVersion);
    }

    @GetMapping
    public ResponseEntity findVersions (@PageableDefault (size = 2)Pageable pageable){
        var find = versionService.versionFindAll(pageable);
        return ResponseEntity.ok(find);
    }

    @GetMapping("/{id}")
    public ResponseEntity findVersionByid(@PathVariable UUID id){
        var findById = versionService.versionFindByid(id);
        return ResponseEntity.ok(findById);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateVersion(@PathVariable UUID id, @RequestBody VersionUpdateRequest versionUpdateRequest){
        var update = versionService.updateVesion(id, versionUpdateRequest);
        return ResponseEntity.ok().body(update);
    }

}
