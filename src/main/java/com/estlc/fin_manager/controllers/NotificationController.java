package com.estlc.fin_manager.controllers;

import jakarta.validation.Valid;

import com.estlc.fin_manager.dto.NotificationRequestDto;
import com.estlc.fin_manager.dto.NotificationResponseDto;
import com.estlc.fin_manager.services.interfaces.NotificationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServiceInterface notificationService;

    @GetMapping
    public List<NotificationResponseDto> findAll() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public NotificationResponseDto findById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationResponseDto create(@Valid @RequestBody NotificationRequestDto dto) {
        return notificationService.saveNotification(dto);
    }

    @PutMapping("/{id}")
    public NotificationResponseDto update(@PathVariable Long id, @Valid @RequestBody NotificationRequestDto dto) {
        return notificationService.updateNotification(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}
