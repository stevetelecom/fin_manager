package com.estlc.fin_manager.dto;

/**
 * DTO de sortie pour Permission.
 */
public record PermissionResponseDto(
        Long idPer,
        String nomPer,
        String descriptionPer
) {
}
