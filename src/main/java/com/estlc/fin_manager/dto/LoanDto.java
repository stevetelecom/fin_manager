package com.estlc.fin_manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de Loan : accountId remplace l'objet Account.
 */
public record LoanDto(
        Long idCredit,
        BigDecimal montantCredit,
        LocalDate dateCredit,
        String typeCredit,
        String motifCredit,
        Long accountId
) {
}
