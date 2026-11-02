package com.estlc.fin_manager.dto;

import com.estlc.fin_manager.utils.StatutCompteEnum;
import com.estlc.fin_manager.utils.TypeCompteEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de Account : userId et agencyId remplacent les objets User et Agency.
 */
public record AccountDto(
        Long numCompte,
        BigDecimal solde,
        String intituleCompte,
        TypeCompteEnum typeCompte,
        LocalDate dateOuverture,
        StatutCompteEnum statutCompte,
        String userId,
        Long agencyId
) {
}
