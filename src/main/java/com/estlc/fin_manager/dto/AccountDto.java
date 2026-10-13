package com.estlc.fin_manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de Account : userId et agencyId remplacent les objets User et Agency.
 */
public record AccountDto(
        Long numCompte,
        BigDecimal solde,
        String intituleCompte,
        String typeCompte,
        LocalDate dateOuverture,
        String statutCompte,
        Long userId,
        Long agencyId
) {
}
