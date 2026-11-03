package com.estlc.fin_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO d'entree pour Notification : transactionId remplace l'objet Transaction.
 */
public record NotificationRequestDto(
        Long idNotification,
        @NotBlank(message = "Le type de notification est obligatoire")
        @Size(max = 50, message = "Le type de notification ne doit pas depasser 50 caracteres")
        String typeNot,
        @NotBlank(message = "Le contenu de la notification est obligatoire")
        @Size(max = 1000, message = "Le contenu de la notification ne doit pas depasser 1000 caracteres")
        String contenuNot,
        @NotNull(message = "La date de la notification est obligatoire")
        LocalDateTime dateNot,
        @NotNull(message = "La transaction (transactionId) est obligatoire")
        Long transactionId
) {
}
