package com.estlc.fin_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO d'entree pour Loan : accountId remplace l'objet Account.
 */
public record LoanRequestDto(
        Long idCredit,
        @NotNull(message = "Le montant du credit est obligatoire")
        @Positive(message = "Le montant du credit doit etre positif")
        BigDecimal montantCredit,
        @NotNull(message = "La date du credit est obligatoire")
        LocalDate dateCredit,
        @NotBlank(message = "Le type de credit est obligatoire")
        @Size(max = 50, message = "Le type de credit ne doit pas depasser 50 caracteres")
        String typeCredit,
        @Size(max = 255, message = "Le motif du credit ne doit pas depasser 255 caracteres")
        String motifCredit,
        @NotNull(message = "Le compte (accountId) est obligatoire")
        Long accountId
) {
}
