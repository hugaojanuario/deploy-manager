package com.hugojanuario.deploy_manager.domain.version.dto;

import com.hugojanuario.deploy_manager.domain.version.Version;

import java.time.LocalDate;

public record VersionResponse(
        String numberVersion,
        LocalDate dataRelease,
        String changelog
) {
    public VersionResponse(Version version){
        this(
                version.getNumberVersion(),
                version.getDateRelease(),
                version.getChangelog()
        );
    }
}
