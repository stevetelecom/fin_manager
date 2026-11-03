package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.NotificationRequestDto;
import com.estlc.fin_manager.dto.NotificationResponseDto;
import com.estlc.fin_manager.entities.Notification;
import com.estlc.fin_manager.entities.Transaction;
import com.estlc.fin_manager.mappers.NotificationMapper;
import com.estlc.fin_manager.repositories.NotificationRepository;
import com.estlc.fin_manager.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final TransactionRepository transactionRepository;
    private final NotificationMapper notificationMapper;

    @Transactional(readOnly = true)
    public List<NotificationResponseDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public NotificationResponseDto findById(Long idNotification) {
        return notificationMapper.toResponseDto(getNotification(idNotification));
    }

    @Transactional
    public NotificationResponseDto create(NotificationRequestDto dto) {
        if (dto.transactionId() == null) {
            throw new IllegalArgumentException("La transaction (transactionId) est obligatoire");
        }

        Notification notification = notificationMapper.toEntity(dto);
        notification.setTransaction(getTransaction(dto.transactionId()));
        return notificationMapper.toResponseDto(notificationRepository.save(notification));
    }

    @Transactional
    public NotificationResponseDto update(Long idNotification, NotificationRequestDto dto) {
        Notification notification = getNotification(idNotification);
        notificationMapper.apply(dto, notification);
        if (dto.transactionId() != null) {
            notification.setTransaction(getTransaction(dto.transactionId()));
        }
        return notificationMapper.toResponseDto(notificationRepository.save(notification));
    }

    @Transactional
    public void delete(Long idNotification) {
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
