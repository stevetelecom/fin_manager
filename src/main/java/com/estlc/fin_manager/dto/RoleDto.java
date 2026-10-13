package com.estlc.fin_manager.dto;

import java.util.Set;

/**
 * DTO de Role : on expose les identifiants des permissions.
 */
public record RoleDto(
        Long idRole,
        String nomRole,
        String description,
        Set<Long> permissionIds
) {
}
