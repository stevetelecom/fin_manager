package com.estlc.fin_manager.dto;

/**
 * DTO d'entrée pour Permission.
 */
public record PermissionRequestDto(
        Long idPer,
        String nomPer,
        String descriptionPer
) {
}
