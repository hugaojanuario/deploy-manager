package com.hugojanuario.deploy_manager.repository;

import com.hugojanuario.deploy_manager.domain.version.Version;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface VersionRepository extends JpaRepository<Version, UUID> {
    Page<Version> findByActiveTrue(Pageable pageable);
}
