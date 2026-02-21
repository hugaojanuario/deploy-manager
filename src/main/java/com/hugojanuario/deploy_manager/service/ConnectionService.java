package com.hugojanuario.deploy_manager.service;

import com.hugojanuario.deploy_manager.domain.client.Client;
import com.hugojanuario.deploy_manager.domain.connection.Connection;
import com.hugojanuario.deploy_manager.domain.connection.dto.ConnectionCreateRequest;
import com.hugojanuario.deploy_manager.domain.connection.dto.ConnectionResponse;
import com.hugojanuario.deploy_manager.repository.ClientRepository;
import com.hugojanuario.deploy_manager.repository.ConnectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public ConnectionResponse createConnection(ConnectionCreateRequest connectionCreateRequest){
        Client client = clientRepository.findById(connectionCreateRequest.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found..."));

        Connection connection = new Connection();
        connection.setClient(client);
        connection.setConnectionType(connectionCreateRequest.connectionType());
        connection.setIdRemoteConnection(connectionCreateRequest.idRemoteConnection());
        connection.setPasswordRemoteConnection(connectionCreateRequest.passwordRemoteConnection());

        Connection saved = connectionRepository.save(connection);

        return new ConnectionResponse(saved);
    }
}
