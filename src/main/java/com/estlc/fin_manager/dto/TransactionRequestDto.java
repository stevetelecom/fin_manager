package com.estlc.fin_manager.dto;

import com.estlc.fin_manager.utils.StatutTransactionEnum;
import com.estlc.fin_manager.utils.TypeTransactionEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO d'entree pour Transaction : accountId remplace l'objet Account.
 */
public record TransactionRequestDto(
        Long numTransac,
        @NotNull(message = "Le type de transaction est obligatoire")
        TypeTransactionEnum typeTransac,
        @NotNull(message = "La date de la transaction est obligatoire")
        LocalDateTime dateTransac,
        @NotNull(message = "Le montant de la transaction est obligatoire")
        @Positive(message = "Le montant de la transaction doit etre positif")
        BigDecimal montantTransac,
        @NotNull(message = "Le statut de la transaction est obligatoire")
        StatutTransactionEnum statutTransac,
        @NotNull(message = "Le compte (accountId) est obligatoire")
        Long accountId
) {
}
