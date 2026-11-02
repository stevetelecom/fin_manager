package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.PermissionDto;
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
    public List<PermissionDto> findAll() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PermissionDto findById(Long idPer) {
        return permissionMapper.toDto(getPermission(idPer));
    }

    @Transactional
    public PermissionDto create(PermissionDto dto) {
        Permission permission = permissionMapper.toEntity(dto);
        return permissionMapper.toDto(permissionRepository.save(permission));
    }

    @Transactional
    public PermissionDto update(Long idPer, PermissionDto dto) {
        Permission permission = getPermission(idPer);
        permissionMapper.apply(dto, permission);
        return permissionMapper.toDto(permissionRepository.save(permission));
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
