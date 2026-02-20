package com.hugojanuario.deploy_manager.service;

import com.hugojanuario.deploy_manager.domain.version.Version;
import com.hugojanuario.deploy_manager.domain.version.dto.VersionCreateRequest;
import com.hugojanuario.deploy_manager.domain.version.dto.VersionResponse;
import com.hugojanuario.deploy_manager.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionService {
    private final VersionRepository versionRepository;

    public VersionResponse createVersion(VersionCreateRequest versionCreateRequest){
        Version version = new Version();
        version.setActive(true);
        version.setNumberVersion(versionCreateRequest.numberVersion());
        version.setDateRelease(versionCreateRequest.dateRelease());
        version.setChangelog(versionCreateRequest.changelog());

        Version saved = versionRepository.save(version);

        return new VersionResponse(saved);
    }
}
