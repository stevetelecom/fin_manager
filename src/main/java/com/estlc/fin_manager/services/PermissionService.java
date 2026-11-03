package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.PermissionRequestDto;
import com.estlc.fin_manager.dto.PermissionResponseDto;
import com.estlc.fin_manager.entities.Permission;
import com.estlc.fin_manager.mappers.PermissionMapper;
import com.estlc.fin_manager.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Transactional(readOnly = true)
    public List<PermissionResponseDto> findAll() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PermissionResponseDto findById(Long idPer) {
        return permissionMapper.toResponseDto(getPermission(idPer));
    }

    @Transactional
    public PermissionResponseDto create(PermissionRequestDto dto) {
        Permission permission = permissionMapper.toEntity(dto);
        return permissionMapper.toResponseDto(permissionRepository.save(permission));
    }

    @Transactional
    public PermissionResponseDto update(Long idPer, PermissionRequestDto dto) {
        Permission permission = getPermission(idPer);
        permissionMapper.apply(dto, permission);
        return permissionMapper.toResponseDto(permissionRepository.save(permission));
    }

    @Transactional
    public void delete(Long idPer) {
        if (!permissionRepository.existsById(idPer)) {
            throw new NoSuchElementException("Permission introuvable : " + idPer);
        }
        permissionRepository.deleteById(idPer);
    }

    private Permission getPermission(Long idPer) {
        return permissionRepository.findById(idPer)
                .orElseThrow(() -> new NoSuchElementException("Permission introuvable : " + idPer));
    }
}
