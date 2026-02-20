package com.hugojanuario.deploy_manager.service;

import com.hugojanuario.deploy_manager.domain.client.Client;
import com.hugojanuario.deploy_manager.domain.client.dto.ClientCreateRequest;
import com.hugojanuario.deploy_manager.domain.client.dto.ClientResponse;
import com.hugojanuario.deploy_manager.domain.client.dto.ClientUpdateRequest;
import com.hugojanuario.deploy_manager.domain.version.Version;
import com.hugojanuario.deploy_manager.repository.ClientRepository;
import com.hugojanuario.deploy_manager.repository.VersionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final VersionRepository versionRepository;

    @Transactional
    public ClientResponse createClient (ClientCreateRequest clientCreateRequest){

        Version version = versionRepository.findById(clientCreateRequest.versionId())
                .orElseThrow(() -> new RuntimeException("Version not found"));

        Client client = new Client();
        client.setName(clientCreateRequest.name());
        client.setCity(clientCreateRequest.city());
        client.setState(clientCreateRequest.state());
        client.setContact(clientCreateRequest.contact());
        client.setActualVersion(version);

        Client saved = clientRepository.save(client);

        return new ClientResponse(saved);
    }

    public Page<ClientResponse> findAllClients(Pageable pageable){
        return clientRepository.findAll(pageable).map(ClientResponse::new);
    }

    public ClientResponse findByIdClient(UUID id){
        Client find = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return new ClientResponse(find);
    }

    public ClientResponse updateClient(UUID id, ClientUpdateRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        if (request.contact() != null) {
            client.setContact(request.contact());
        }

        if (request.actualVersion() != null) {
            Version version = versionRepository.findById(request.actualVersion().getId())
                    .orElseThrow(() -> new RuntimeException("Version not found"));
            client.setActualVersion(version);
        }

        Client up = clientRepository.save(client);
        return new ClientResponse(up);
    }

    public void deleteCLient(UUID id){
        clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        clientRepository.deleteById(id);
    }
}
