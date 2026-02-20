package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.version.dto.VersionCreateRequest;
import com.hugojanuario.deploy_manager.service.VersionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/version")
@RequiredArgsConstructor
public class VersionController {

    private final VersionService versionService;

    @PostMapping
    public ResponseEntity createdVersion (@RequestBody @Valid VersionCreateRequest versionCreateRequest){
        var newVersion = versionService.createVersion(versionCreateRequest);

        return ResponseEntity.ok().body(newVersion);
    }
}
