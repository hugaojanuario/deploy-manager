package com.hugojanuario.deploy_manager.domain.connection.dto;

import com.hugojanuario.deploy_manager.domain.connection.Connection;
import com.hugojanuario.deploy_manager.domain.connection.ConnectionType;

import java.util.UUID;

public record ConnectionResponse(
        UUID id,
        ConnectionType connectionType,
        String idRemoteConnection,
        String passwordRemoteConnection
) {
    public ConnectionResponse(Connection connection){
        this(
                connection.getId(),
                connection.getConnectionType(),
                connection.getIdRemoteConnection(),
                connection.getPasswordRemoteConnection()
        );
    }
}
