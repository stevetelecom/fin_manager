package com.estlc.fin_manager.dto;

import java.time.LocalDateTime;

/**
 * DTO de Notification : transactionId remplace l'objet Transaction.
 */
public record NotificationDto(
        Long idNotification,
        String typeNot,
        String contenuNot,
        LocalDateTime dateNot,
        Long transactionId
) {
}
