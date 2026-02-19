package com.hugojanuario.deploy_manager.domain.client.dto;

import com.hugojanuario.deploy_manager.domain.version.Version;

import java.util.UUID;

public record ClientCreateRequest(
        String name,
        String city,
        String state,
        String contact,
        UUID versionId
) {
}
