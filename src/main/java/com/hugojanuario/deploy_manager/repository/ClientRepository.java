package com.hugojanuario.deploy_manager.repository;

import com.hugojanuario.deploy_manager.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
