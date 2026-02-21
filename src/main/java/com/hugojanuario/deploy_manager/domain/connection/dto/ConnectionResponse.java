package com.hugojanuario.deploy_manager.domain.connection.dto;

import com.hugojanuario.deploy_manager.domain.connection.Connection;
import com.hugojanuario.deploy_manager.domain.connection.ConnectionType;

public record ConnectionResponse(
        ConnectionType connectionType,
        String idRemoteConnection,
        String passwordRemoteConnection
) {
    public ConnectionResponse(Connection connection){
        this(
                connection.getConnectionType(),
                connection.getIdRemoteConnection(),
                connection.getPasswordRemoteConnection()
        );
    }
}
