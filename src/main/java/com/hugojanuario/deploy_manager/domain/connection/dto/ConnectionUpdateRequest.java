package com.hugojanuario.deploy_manager.domain.connection.dto;

import com.hugojanuario.deploy_manager.domain.connection.ConnectionType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record ConnectionUpdateRequest(

        @NotNull
        ConnectionType connectionType,

        @NotNull
        String idRemoteConnection,

        @NotNull
        String passwordRemoteConnection

) {
}
