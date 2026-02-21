package com.hugojanuario.deploy_manager.repository;

import com.hugojanuario.deploy_manager.domain.connection.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConnectionRepository extends JpaRepository<Connection, UUID> {
}
