package com.estlc.fin_manager.dto;

import com.estlc.fin_manager.utils.StatutTransactionEnum;
import com.estlc.fin_manager.utils.TypeTransactionEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO de sortie pour Transaction : accountId remplace l'objet Account.
 */
public record TransactionResponseDto(
        Long numTransac,
        TypeTransactionEnum typeTransac,
        LocalDateTime dateTransac,
        BigDecimal montantTransac,
        StatutTransactionEnum statutTransac,
        Long accountId
) {
}
