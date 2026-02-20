package com.hugojanuario.deploy_manager.domain.client.dto;

import com.hugojanuario.deploy_manager.domain.client.Client;
import com.hugojanuario.deploy_manager.domain.version.Version;

import java.util.UUID;

public record ClientResponse(
        UUID id,
        String name,
        String city,
        String state,
        String contact,
        UUID versionId,
        String numberVersion,
        String userMachineServer,
        String passwordMachineServer,
        String userDb,
        String passwordDb

) {
    public ClientResponse(Client client){
        this(
                client.getId(),
                client.getName(),
                client.getCity(),
                client.getState(),
                client.getContact(),
                client.getActualVersion().getId(),
                client.getActualVersion().getNumberVersion(),
                client.getUserMachineServer(),
                client.getPasswordMachineServer(),
                client.getUserDb(),
                client.getPasswordDb()
        );
    }
}
