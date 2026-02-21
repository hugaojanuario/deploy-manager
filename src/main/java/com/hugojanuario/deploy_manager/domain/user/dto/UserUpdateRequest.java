package com.hugojanuario.deploy_manager.domain.user.dto;

import com.hugojanuario.deploy_manager.domain.user.RoleType;

public record UserUpdateRequest(
        String email,
        String password,
        RoleType roleType
) {
}
