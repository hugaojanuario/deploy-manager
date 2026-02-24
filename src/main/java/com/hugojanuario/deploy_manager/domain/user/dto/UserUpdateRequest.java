package com.hugojanuario.deploy_manager.domain.user.dto;

import com.hugojanuario.deploy_manager.domain.user.RoleType;
import jakarta.validation.constraints.Email;

public record UserUpdateRequest(

        @Email
        String email,
        String password,
        RoleType roleType
) {
}
