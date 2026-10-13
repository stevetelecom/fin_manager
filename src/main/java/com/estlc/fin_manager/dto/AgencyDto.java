package com.estlc.fin_manager.dto;

/**
 * DTO de Agency.
 */
public record AgencyDto(
        Long numAg,
        Double longitude,
        Double latitude,
        String nomAg,
        String telAg,
        String emailAg
) {
}
