package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.PermissionRequestDto;
import com.estlc.fin_manager.dto.PermissionResponseDto;
import com.estlc.fin_manager.entities.Permission;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper MapStruct de Permission.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionMapper {

    PermissionResponseDto toDTO(Permission permission);

    @Mapping(target = "roles", ignore = true)
    Permission toDTOEntity(PermissionRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idPer", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateEntityFromDto(PermissionRequestDto dto, @MappingTarget Permission permission);
}
