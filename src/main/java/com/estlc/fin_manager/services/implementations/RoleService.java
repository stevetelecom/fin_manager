package com.estlc.fin_manager.services.implementations;

import com.estlc.fin_manager.dto.RoleRequestDto;
import com.estlc.fin_manager.dto.RoleResponseDto;
import com.estlc.fin_manager.entities.Permission;
import com.estlc.fin_manager.entities.Role;
import com.estlc.fin_manager.mappers.RoleMapper;
import com.estlc.fin_manager.repositories.PermissionRepository;
import com.estlc.fin_manager.repositories.RoleRepository;
import com.estlc.fin_manager.services.interfaces.RoleServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponseDto getRoleById(Long idRole) {
        return roleMapper.toDTO(getRole(idRole));
    }

    @Override
    @Transactional
    public RoleResponseDto saveRole(RoleRequestDto dto) {
        Role role = roleMapper.toDTOEntity(dto);
        role.setPermissions(resolvePermissions(dto.permissionIds()));
        return roleMapper.toDTO(roleRepository.save(role));
    }

    @Override
    @Transactional
    public RoleResponseDto updateRole(Long idRole, RoleRequestDto dto) {
        Role role = getRole(idRole);
        roleMapper.updateEntityFromDto(dto, role);
        if (dto.permissionIds() != null) {
            role.setPermissions(resolvePermissions(dto.permissionIds()));
        }
        return roleMapper.toDTO(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void deleteRole(Long idRole) {
        if (!roleRepository.existsById(idRole)) {
            throw new NoSuchElementException("Rôle introuvable : " + idRole);
        }
        roleRepository.deleteById(idRole);
    }

    private Role getRole(Long idRole) {
        return roleRepository.findById(idRole)
                .orElseThrow(() -> new NoSuchElementException("Rôle introuvable : " + idRole));
    }

    private List<Permission> resolvePermissions(Set<Long> permissionIds) {
        List<Permission> permissions = new ArrayList<>();
        if (permissionIds == null) {
            return permissions;
        }
        for (Long permissionId : permissionIds) {
            Permission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new NoSuchElementException("Permission introuvable : " + permissionId));
            permissions.add(permission);
        }
        return permissions;
    }
}
