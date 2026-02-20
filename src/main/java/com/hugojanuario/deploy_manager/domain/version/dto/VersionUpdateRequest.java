package com.hugojanuario.deploy_manager.domain.version.dto;

import java.time.LocalDate;

public record VersionUpdateRequest(
        String changelog
) {
}
