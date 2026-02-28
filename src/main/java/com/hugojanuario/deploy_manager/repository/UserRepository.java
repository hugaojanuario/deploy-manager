package com.hugojanuario.deploy_manager.repository;

import com.hugojanuario.deploy_manager.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Page<User> findByActiveTrue(Pageable pageable);

    UserDetails findByUsername(String username);
}
