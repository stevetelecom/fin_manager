package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.RoleRequestDto;
import com.estlc.fin_manager.dto.RoleResponseDto;
import com.estlc.fin_manager.entities.Permission;
import com.estlc.fin_manager.entities.Role;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper MapStruct de Role.
 * La relation permissions est resolue par le service.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    @Mapping(source = "permissions", target = "permissionIds", qualifiedByName = "permissionListToIdSet")
    RoleResponseDto toDTO(Role role);

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    Role toDTOEntity(RoleRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idRole", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    void updateEntityFromDto(RoleRequestDto dto, @MappingTarget Role role);

    @Named("permissionListToIdSet")
    default Set<Long> permissionListToIdSet(List<Permission> permissions) {
        if (permissions == null) {
            return Set.of();
        }
        return permissions.stream()
                .map(Permission::getIdPer)
                .collect(Collectors.toSet());
    }
}
