package com.estlc.fin_manager.dto;

import com.estlc.fin_manager.utils.StatutCompteEnum;
import com.estlc.fin_manager.utils.TypeCompteEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO d'entree pour Account : userId et agencyId remplacent les objets User et Agency.
 */
public record AccountRequestDto(
        Long numCompte,
        @PositiveOrZero(message = "Le solde ne peut pas etre negatif")
        BigDecimal solde,
        @NotBlank(message = "L'intitule du compte est obligatoire")
        @Size(max = 150, message = "L'intitule du compte ne doit pas depasser 150 caracteres")
        String intituleCompte,
        @NotNull(message = "Le type de compte est obligatoire")
        TypeCompteEnum typeCompte,
        @NotNull(message = "La date d'ouverture est obligatoire")
        LocalDate dateOuverture,
        @NotNull(message = "Le statut du compte est obligatoire")
        StatutCompteEnum statutCompte,
        @NotBlank(message = "Le userId (CNI du proprietaire) est obligatoire")
        String userId,
        Long agencyId
) {
}
