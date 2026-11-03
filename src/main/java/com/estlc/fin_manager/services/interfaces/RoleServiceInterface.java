package com.estlc.fin_manager.services.interfaces;

import com.estlc.fin_manager.dto.RoleRequestDto;
import com.estlc.fin_manager.dto.RoleResponseDto;

import java.util.List;

/**
 * Contrat metier de Role.
 */
public interface RoleServiceInterface {

    RoleResponseDto saveRole(RoleRequestDto roleRequestDto);

    RoleResponseDto updateRole(Long idRole, RoleRequestDto roleRequestDto);

    void deleteRole(Long idRole);

    List<RoleResponseDto> getAllRoles();

    RoleResponseDto getRoleById(Long idRole);
}
