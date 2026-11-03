package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.AccountRequestDto;
import com.estlc.fin_manager.dto.AccountResponseDto;
import com.estlc.fin_manager.entities.Account;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper MapStruct de Account.
 * Les relations (user, agency) sont resolues par le service.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    @Mapping(source = "user.idUser", target = "userId")
    @Mapping(source = "agency.numAg", target = "agencyId")
    AccountResponseDto toDTO(Account account);

    @Mapping(target = "solde", defaultExpression = "java(java.math.BigDecimal.ZERO)")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "agency", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "loans", ignore = true)
    Account toDTOEntity(AccountRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "numCompte", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "agency", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "loans", ignore = true)
    void updateEntityFromDto(AccountRequestDto dto, @MappingTarget Account account);
}
