package com.estlc.fin_manager.dto;

import java.util.Set;

/**
 * DTO de sortie pour Role : on expose les identifiants des permissions.
 */
public record RoleResponseDto(
        Long idRole,
        String nomRole,
        String description,
        Set<Long> permissionIds
) {
}
