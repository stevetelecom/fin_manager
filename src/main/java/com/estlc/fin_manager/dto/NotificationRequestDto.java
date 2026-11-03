package com.estlc.fin_manager.dto;

import java.time.LocalDateTime;

/**
 * DTO d'entrée pour Notification : transactionId remplace l'objet Transaction.
 */
public record NotificationRequestDto(
        Long idNotification,
        String typeNot,
        String contenuNot,
        LocalDateTime dateNot,
        Long transactionId
) {
}
