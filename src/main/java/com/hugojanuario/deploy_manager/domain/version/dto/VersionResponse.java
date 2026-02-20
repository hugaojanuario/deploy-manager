package com.hugojanuario.deploy_manager.domain.version.dto;

import com.hugojanuario.deploy_manager.domain.version.Version;

import java.time.LocalDate;
import java.util.UUID;

public record VersionResponse(
        UUID id,
        String numberVersion,
        LocalDate dataRelease,
        String changelog
) {
    public VersionResponse(Version version){
        this(
                version.getId(),
                version.getNumberVersion(),
                version.getDateRelease(),
                version.getChangelog()
        );
    }
}
