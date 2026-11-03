package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.NotificationRequestDto;
import com.estlc.fin_manager.dto.NotificationResponseDto;
import com.estlc.fin_manager.entities.Notification;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper MapStruct de Notification.
 * La relation transaction est resolue par le service.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {

    @Mapping(source = "transaction.numTransac", target = "transactionId")
    NotificationResponseDto toDTO(Notification notification);

    @Mapping(target = "transaction", ignore = true)
    Notification toDTOEntity(NotificationRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idNotification", ignore = true)
    @Mapping(target = "transaction", ignore = true)
    void updateEntityFromDto(NotificationRequestDto dto, @MappingTarget Notification notification);
}
