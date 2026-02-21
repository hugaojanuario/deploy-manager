package com.hugojanuario.deploy_manager.service;

import com.hugojanuario.deploy_manager.domain.client.Client;
import com.hugojanuario.deploy_manager.domain.connection.Connection;
import com.hugojanuario.deploy_manager.domain.connection.dto.ConnectionCreateRequest;
import com.hugojanuario.deploy_manager.domain.connection.dto.ConnectionResponse;
import com.hugojanuario.deploy_manager.domain.connection.dto.ConnectionUpdateRequest;
import com.hugojanuario.deploy_manager.domain.version.Version;
import com.hugojanuario.deploy_manager.repository.ClientRepository;
import com.hugojanuario.deploy_manager.repository.ConnectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

//    GET /api/client/{id}/connections
//    public ConnectionResponse findByClient(Client client){
//        var findByClient = connectionRepository.findById(client.getId())
//                .orElseThrow(() -> new RuntimeException("Client not found..."));
//
//        return ConnectionResponse(findByClient);
//    }

    public ConnectionResponse findByIdConnection(UUID id){
        Connection connection = connectionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Connection not found..."));

        return new ConnectionResponse(connection);
    }

    public ConnectionResponse updateConnection(UUID id, ConnectionUpdateRequest connectionUpdateRequest){
        Connection connection = connectionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Connection not found..."));

        if (connectionUpdateRequest.connectionType() != null){
            connection.setConnectionType(connectionUpdateRequest.connectionType());
        }

        if(connectionUpdateRequest.idRemoteConnection() != null){
            connection.setIdRemoteConnection(connectionUpdateRequest.idRemoteConnection());
        }

        if(connectionUpdateRequest.passwordRemoteConnection() != null){
            connection.setPasswordRemoteConnection(connectionUpdateRequest.passwordRemoteConnection());
        }

        Connection update = connectionRepository.save(connection);

        return new ConnectionResponse(update);

    }

    public void deleteConnection (UUID id){
        connectionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Coonnection not found..."));

        connectionRepository.deleteById(id);
    }


}
