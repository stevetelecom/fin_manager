package com.estlc.fin_manager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO de Transaction : accountId remplace l'objet Account.
 */
public record TransactionDto(
        Long numTransac,
        String typeTransac,
        LocalDateTime dateTransac,
        BigDecimal montantTransac,
        String statutTransac,
        Long accountId
) {
}
