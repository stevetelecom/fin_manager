package com.estlc.fin_manager.services.interfaces;

import com.estlc.fin_manager.dto.NotificationRequestDto;
import com.estlc.fin_manager.dto.NotificationResponseDto;

import java.util.List;

/**
 * Contrat metier de Notification.
 */
public interface NotificationServiceInterface {

    NotificationResponseDto saveNotification(NotificationRequestDto notificationRequestDto);

    NotificationResponseDto updateNotification(Long idNotification, NotificationRequestDto notificationRequestDto);

    void deleteNotification(Long idNotification);

    List<NotificationResponseDto> getAllNotifications();

    NotificationResponseDto getNotificationById(Long idNotification);
}
