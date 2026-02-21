package com.hugojanuario.deploy_manager.domain.connection.dto;

import com.hugojanuario.deploy_manager.domain.connection.ConnectionType;

public record ConnectionUpdateRequest(
        ConnectionType connectionType,
        String idRemoteConnection,
        String passwordRemoteConnection

) {
}
