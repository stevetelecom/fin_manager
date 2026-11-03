package com.estlc.fin_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

/**
 * DTO d'entree pour Role : on expose les identifiants des permissions.
 */
public record RoleRequestDto(
        Long idRole,
        @NotBlank(message = "Le nom du role est obligatoire")
        @Size(max = 50, message = "Le nom du role ne doit pas depasser 50 caracteres")
        String nomRole,
        @Size(max = 255, message = "La description du role ne doit pas depasser 255 caracteres")
        String description,
        Set<Long> permissionIds
) {
}
