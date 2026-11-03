package com.estlc.fin_manager.services.interfaces;

import com.estlc.fin_manager.dto.PermissionRequestDto;
import com.estlc.fin_manager.dto.PermissionResponseDto;

import java.util.List;

/**
 * Contrat metier de Permission.
 */
public interface PermissionServiceInterface {

    PermissionResponseDto savePermission(PermissionRequestDto permissionRequestDto);

    PermissionResponseDto updatePermission(Long idPer, PermissionRequestDto permissionRequestDto);

    void deletePermission(Long idPer);

    List<PermissionResponseDto> getAllPermissions();

    PermissionResponseDto getPermissionById(Long idPer);
}
