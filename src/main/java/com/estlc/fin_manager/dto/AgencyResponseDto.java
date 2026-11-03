package com.estlc.fin_manager.dto;

/**
 * DTO de sortie pour Agency.
 */
public record AgencyResponseDto(
        Long numAg,
        Double longitude,
        Double latitude,
        String nomAg,
        String telAg,
        String emailAg
) {
}
