package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.RoleRequestDto;
import com.estlc.fin_manager.dto.RoleResponseDto;
import com.estlc.fin_manager.entities.Permission;
import com.estlc.fin_manager.entities.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleResponseDto toResponseDto(Role role) {
        Set<Long> permissionIds = role.getPermissions() == null
                ? Set.of()
                : role.getPermissions().stream()
                        .map(Permission::getIdPer)
                        .collect(Collectors.toSet());

        return new RoleResponseDto(
                role.getIdRole(),
                role.getNomRole(),
                role.getDescription(),
                permissionIds
        );
    }

    public Role toEntity(RoleRequestDto dto) {
        Role role = new Role();
        role.setIdRole(dto.idRole());
        apply(dto, role);
        return role;
    }

    public void apply(RoleRequestDto dto, Role role) {
        role.setNomRole(dto.nomRole());
        role.setDescription(dto.description());
    }
}
