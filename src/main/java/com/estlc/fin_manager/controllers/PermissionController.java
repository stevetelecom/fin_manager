package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.PermissionDto;
import com.estlc.fin_manager.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    public List<PermissionDto> findAll() {
        return permissionService.findAll();
    }

    @GetMapping("/{id}")
    public PermissionDto findById(@PathVariable Long id) {
        return permissionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionDto create(@RequestBody PermissionDto dto) {
        return permissionService.create(dto);
    }

    @PutMapping("/{id}")
    public PermissionDto update(@PathVariable Long id, @RequestBody PermissionDto dto) {
        return permissionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        permissionService.delete(id);
    }
}
