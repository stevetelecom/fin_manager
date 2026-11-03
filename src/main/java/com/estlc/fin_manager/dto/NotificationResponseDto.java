package com.estlc.fin_manager.dto;

import java.time.LocalDateTime;

/**
 * DTO de sortie pour Notification : transactionId remplace l'objet Transaction.
 */
public record NotificationResponseDto(
        Long idNotification,
        String typeNot,
        String contenuNot,
        LocalDateTime dateNot,
        Long transactionId
) {
}
