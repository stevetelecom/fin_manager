package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.NotificationDto;
import com.estlc.fin_manager.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDto> findAll() {
        return notificationService.findAll();
    }

    @GetMapping("/{id}")
    public NotificationDto findById(@PathVariable Long id) {
        return notificationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationDto create(@RequestBody NotificationDto dto) {
        return notificationService.create(dto);
    }

    @PutMapping("/{id}")
    public NotificationDto update(@PathVariable Long id, @RequestBody NotificationDto dto) {
        return notificationService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        notificationService.delete(id);
    }
}
