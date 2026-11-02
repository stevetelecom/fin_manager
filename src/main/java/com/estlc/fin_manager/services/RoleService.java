package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.RoleDto;
import com.estlc.fin_manager.entities.Permission;
import com.estlc.fin_manager.entities.Role;
import com.estlc.fin_manager.mappers.RoleMapper;
import com.estlc.fin_manager.repositories.PermissionRepository;
import com.estlc.fin_manager.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;

    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public RoleDto findById(Long idRole) {
        return roleMapper.toDto(getRole(idRole));
    }

    @Transactional
    public RoleDto create(RoleDto dto) {
        Role role = roleMapper.toEntity(dto);
        role.setPermissions(resolvePermissions(dto.permissionIds()));
        return roleMapper.toDto(roleRepository.save(role));
    }

    @Transactional
    public RoleDto update(Long idRole, RoleDto dto) {
        Role role = getRole(idRole);
        roleMapper.apply(dto, role);
        if (dto.permissionIds() != null) {
            role.setPermissions(resolvePermissions(dto.permissionIds()));
        }
        return roleMapper.toDto(roleRepository.save(role));
    }

    @Transactional
    public void delete(Long idRole) {
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
