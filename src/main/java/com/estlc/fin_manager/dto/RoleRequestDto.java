package com.estlc.fin_manager.dto;

import java.util.Set;

/**
 * DTO d'entrée pour Role : on expose les identifiants des permissions.
 */
public record RoleRequestDto(
        Long idRole,
        String nomRole,
        String description,
        Set<Long> permissionIds
) {
}
