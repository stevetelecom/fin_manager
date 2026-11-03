package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.AgencyRequestDto;
import com.estlc.fin_manager.dto.AgencyResponseDto;
import com.estlc.fin_manager.entities.Agency;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper MapStruct de Agency.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AgencyMapper {

    AgencyResponseDto toDTO(Agency agency);

    @Mapping(target = "accounts", ignore = true)
    Agency toDTOEntity(AgencyRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "numAg", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    void updateEntityFromDto(AgencyRequestDto dto, @MappingTarget Agency agency);
}
