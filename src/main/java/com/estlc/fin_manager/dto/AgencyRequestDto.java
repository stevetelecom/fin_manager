package com.estlc.fin_manager.dto;

/**
 * DTO d'entrée pour Agency.
 */
public record AgencyRequestDto(
        Long numAg,
        Double longitude,
        Double latitude,
        String nomAg,
        String telAg,
        String emailAg
) {
}
