package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.RoleDto;
import com.estlc.fin_manager.entities.Permission;
import com.estlc.fin_manager.entities.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleDto toDto(Role role) {
        Set<Long> permissionIds = role.getPermissions() == null
                ? Set.of()
                : role.getPermissions().stream()
                        .map(Permission::getIdPer)
                        .collect(Collectors.toSet());

        return new RoleDto(
                role.getIdRole(),
                role.getNomRole(),
                role.getDescription(),
                permissionIds
        );
    }

    public Role toEntity(RoleDto dto) {
        Role role = new Role();
        role.setIdRole(dto.idRole());
        apply(dto, role);
        return role;
    }

    public void apply(RoleDto dto, Role role) {
        role.setNomRole(dto.nomRole());
        role.setDescription(dto.description());
    }
}
