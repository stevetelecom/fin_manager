package com.estlc.fin_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO d'entree pour Permission.
 */
public record PermissionRequestDto(
        Long idPer,
        @NotBlank(message = "Le nom de la permission est obligatoire")
        @Size(max = 100, message = "Le nom de la permission ne doit pas depasser 100 caracteres")
        String nomPer,
        @Size(max = 255, message = "La description de la permission ne doit pas depasser 255 caracteres")
        String descriptionPer
) {
}
