package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.PermissionRequestDto;
import com.estlc.fin_manager.dto.PermissionResponseDto;
import com.estlc.fin_manager.services.interfaces.PermissionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionServiceInterface permissionService;

    @GetMapping
    public List<PermissionResponseDto> findAll() {
        return permissionService.getAllPermissions();
    }

    @GetMapping("/{id}")
    public PermissionResponseDto findById(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponseDto create(@RequestBody PermissionRequestDto dto) {
        return permissionService.savePermission(dto);
    }

    @PutMapping("/{id}")
    public PermissionResponseDto update(@PathVariable Long id, @RequestBody PermissionRequestDto dto) {
        return permissionService.updatePermission(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        permissionService.deletePermission(id);
    }
}
