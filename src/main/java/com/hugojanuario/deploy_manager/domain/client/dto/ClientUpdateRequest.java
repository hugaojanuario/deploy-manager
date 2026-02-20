package com.hugojanuario.deploy_manager.domain.client.dto;

import com.hugojanuario.deploy_manager.domain.version.Version;

public record ClientUpdateRequest(
        String contact,
        Version actualVersion,
        String userMachineServer,
        String passwordMachineServer,
        String userDb,
        String passwordDb

) {
}
