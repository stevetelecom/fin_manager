package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.LoanRequestDto;
import com.estlc.fin_manager.dto.LoanResponseDto;
import com.estlc.fin_manager.entities.Loan;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper MapStruct de Loan.
 * La relation account est resolue par le service.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {

    @Mapping(source = "account.numCompte", target = "accountId")
    LoanResponseDto toDTO(Loan loan);

    @Mapping(target = "account", ignore = true)
    Loan toDTOEntity(LoanRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idCredit", ignore = true)
    @Mapping(target = "account", ignore = true)
    void updateEntityFromDto(LoanRequestDto dto, @MappingTarget Loan loan);
}
