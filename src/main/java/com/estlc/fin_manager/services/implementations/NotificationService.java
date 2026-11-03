package com.estlc.fin_manager.services.implementations;

import com.estlc.fin_manager.dto.NotificationRequestDto;
import com.estlc.fin_manager.dto.NotificationResponseDto;
import com.estlc.fin_manager.entities.Notification;
import com.estlc.fin_manager.entities.Transaction;
import com.estlc.fin_manager.mappers.NotificationMapper;
import com.estlc.fin_manager.repositories.NotificationRepository;
import com.estlc.fin_manager.repositories.TransactionRepository;
import com.estlc.fin_manager.services.interfaces.NotificationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationServiceInterface {

    private final NotificationRepository notificationRepository;
    private final TransactionRepository transactionRepository;
    private final NotificationMapper notificationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDto> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponseDto getNotificationById(Long idNotification) {
        return notificationMapper.toDTO(getNotification(idNotification));
    }

    @Override
    @Transactional
    public NotificationResponseDto saveNotification(NotificationRequestDto dto) {
        if (dto.transactionId() == null) {
            throw new IllegalArgumentException("La transaction (transactionId) est obligatoire");
        }

        Notification notification = notificationMapper.toDTOEntity(dto);
        notification.setTransaction(getTransaction(dto.transactionId()));
        return notificationMapper.toDTO(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public NotificationResponseDto updateNotification(Long idNotification, NotificationRequestDto dto) {
        Notification notification = getNotification(idNotification);
        notificationMapper.updateEntityFromDto(dto, notification);
        if (dto.transactionId() != null) {
            notification.setTransaction(getTransaction(dto.transactionId()));
        }
        return notificationMapper.toDTO(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public void deleteNotification(Long idNotification) {
        if (!notificationRepository.existsById(idNotification)) {
            throw new NoSuchElementException("Notification introuvable : " + idNotification);
        }
        notificationRepository.deleteById(idNotification);
    }

    private Notification getNotification(Long idNotification) {
        return notificationRepository.findById(idNotification)
                .orElseThrow(() -> new NoSuchElementException("Notification introuvable : " + idNotification));
    }

    private Transaction getTransaction(Long numTransac) {
        return transactionRepository.findById(numTransac)
                .orElseThrow(() -> new NoSuchElementException("Transaction introuvable : " + numTransac));
    }
}
