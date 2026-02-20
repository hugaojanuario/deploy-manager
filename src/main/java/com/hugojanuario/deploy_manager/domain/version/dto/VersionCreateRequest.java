package com.hugojanuario.deploy_manager.domain.version.dto;

import java.time.LocalDate;

public record VersionCreateRequest(
        String numberVersion,
        LocalDate dateRelease,
        String changelog
) {
}
