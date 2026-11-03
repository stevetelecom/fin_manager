package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.NotificationRequestDto;
import com.estlc.fin_manager.dto.NotificationResponseDto;
import com.estlc.fin_manager.entities.Notification;
import com.estlc.fin_manager.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationResponseDto toResponseDto(Notification notification) {
        Transaction transaction = notification.getTransaction();

        return new NotificationResponseDto(
                notification.getIdNotification(),
                notification.getTypeNot(),
                notification.getContenuNot(),
                notification.getDateNot(),
                transaction != null ? transaction.getNumTransac() : null
        );
    }

    public Notification toEntity(NotificationRequestDto dto) {
        Notification notification = new Notification();
        notification.setIdNotification(dto.idNotification());
        apply(dto, notification);
        return notification;
    }

    public void apply(NotificationRequestDto dto, Notification notification) {
        notification.setTypeNot(dto.typeNot());
        notification.setContenuNot(dto.contenuNot());
        notification.setDateNot(dto.dateNot());
    }
}
