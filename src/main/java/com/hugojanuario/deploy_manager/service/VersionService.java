package com.hugojanuario.deploy_manager.service;

import com.hugojanuario.deploy_manager.domain.client.Client;
import com.hugojanuario.deploy_manager.domain.version.Version;
import com.hugojanuario.deploy_manager.domain.version.dto.VersionCreateRequest;
import com.hugojanuario.deploy_manager.domain.version.dto.VersionResponse;
import com.hugojanuario.deploy_manager.domain.version.dto.VersionUpdateRequest;
import com.hugojanuario.deploy_manager.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VersionService {
    private final VersionRepository versionRepository;

    public VersionResponse createVersion(VersionCreateRequest versionCreateRequest){
        Version version = new Version();
        version.setActive(true);
        version.getId();
        version.setNumberVersion(versionCreateRequest.numberVersion());
        version.setDateRelease(versionCreateRequest.dateRelease());
        version.setChangelog(versionCreateRequest.changelog());

        Version saved = versionRepository.save(version);

        return new VersionResponse(saved);
    }

    public Page<VersionResponse> versionFindAll(Pageable pageable){
        return versionRepository.findByActiveTrue(pageable).map(VersionResponse::new);
    }

    public VersionResponse versionFindByid(UUID id){
        Version version = versionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID not found..."));
        return new VersionResponse(version);
    }

    public VersionResponse updateVesion(UUID id, VersionUpdateRequest request){
        Version version = versionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Version not found"));

        if(request.changelog() != null){
            version.setChangelog(request.changelog());
        }

        Version up = versionRepository.save(version);
        return new VersionResponse(up);
    }

    public void deleteVersion(UUID id){
        Version version = versionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        version.setActive(false);
        versionRepository.save(version);
    }
}
