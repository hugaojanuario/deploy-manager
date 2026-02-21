package com.hugojanuario.deploy_manager.domain.user.dto;

import com.hugojanuario.deploy_manager.domain.user.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UserCreateRequest(
        String nameUser,
        String email,
        String password,
        RoleType roleType
) {
}
