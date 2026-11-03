package com.estlc.fin_manager.services.implementations;

import com.estlc.fin_manager.dto.PermissionRequestDto;
import com.estlc.fin_manager.dto.PermissionResponseDto;
import com.estlc.fin_manager.entities.Permission;
import com.estlc.fin_manager.mappers.PermissionMapper;
import com.estlc.fin_manager.repositories.PermissionRepository;
import com.estlc.fin_manager.services.interfaces.PermissionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PermissionService implements PermissionServiceInterface {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponseDto> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponseDto getPermissionById(Long idPer) {
        return permissionMapper.toDTO(getPermission(idPer));
    }

    @Override
    @Transactional
    public PermissionResponseDto savePermission(PermissionRequestDto dto) {
        Permission permission = permissionMapper.toDTOEntity(dto);
        return permissionMapper.toDTO(permissionRepository.save(permission));
    }

    @Override
    @Transactional
    public PermissionResponseDto updatePermission(Long idPer, PermissionRequestDto dto) {
        Permission permission = getPermission(idPer);
        permissionMapper.updateEntityFromDto(dto, permission);
        return permissionMapper.toDTO(permissionRepository.save(permission));
    }

    @Override
    @Transactional
    public void deletePermission(Long idPer) {
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
