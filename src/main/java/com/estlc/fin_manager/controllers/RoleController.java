package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.RoleRequestDto;
import com.estlc.fin_manager.dto.RoleResponseDto;
import com.estlc.fin_manager.services.interfaces.RoleServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceInterface roleService;

    @GetMapping
    public List<RoleResponseDto> findAll() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleResponseDto findById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDto create(@RequestBody RoleRequestDto dto) {
        return roleService.saveRole(dto);
    }

    @PutMapping("/{id}")
    public RoleResponseDto update(@PathVariable Long id, @RequestBody RoleRequestDto dto) {
        return roleService.updateRole(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
