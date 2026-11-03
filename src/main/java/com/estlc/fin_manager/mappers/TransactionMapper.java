package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.TransactionRequestDto;
import com.estlc.fin_manager.dto.TransactionResponseDto;
import com.estlc.fin_manager.entities.Transaction;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper MapStruct de Transaction.
 * La relation account est resolue par le service.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

    @Mapping(source = "account.numCompte", target = "accountId")
    TransactionResponseDto toDTO(Transaction transaction);

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "notification", ignore = true)
    Transaction toDTOEntity(TransactionRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "numTransac", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "notification", ignore = true)
    void updateEntityFromDto(TransactionRequestDto dto, @MappingTarget Transaction transaction);
}
