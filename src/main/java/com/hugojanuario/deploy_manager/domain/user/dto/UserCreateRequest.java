package com.hugojanuario.deploy_manager.domain.user.dto;

import com.hugojanuario.deploy_manager.domain.user.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;

public record UserCreateRequest(
        String nameUser,

        @Email
        String email,
        String password,
        RoleType roleType
) {
}
