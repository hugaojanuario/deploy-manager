package com.hugojanuario.deploy_manager.domain.user.dto;

import com.hugojanuario.deploy_manager.domain.user.RoleType;
import com.hugojanuario.deploy_manager.domain.user.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String nameUser,
        String email,
        RoleType roleType
) {
    public UserResponse(User user){
        this(
                user.getId(),
                user.getNameUser(),
                user.getEmail(),
                user.getRoleType()
        );
    }
}
