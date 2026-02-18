package com.hugojanuario.deploy_manager.domain.client.dto;

import com.hugojanuario.deploy_manager.domain.version.Version;

public record ClientCreateRequest(
        String name,
        String city,
        String state,
        String contact,
        Version actualVersion
) {
}
