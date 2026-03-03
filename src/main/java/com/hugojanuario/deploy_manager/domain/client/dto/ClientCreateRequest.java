package com.hugojanuario.deploy_manager.domain.client.dto;

import com.hugojanuario.deploy_manager.domain.version.Version;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClientCreateRequest(

        @NotNull
        String name,

        @NotNull
        String city,

        @NotNull
        String state,

        @NotNull
        String contact,

        @NotNull
        UUID versionId,

        @NotNull
        String userMachineServer,

        @NotNull
        String passwordMachineServer,

        @NotNull
        String userDb,

        @NotNull
        String passwordDb
) {
}
