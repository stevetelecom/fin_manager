package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.UserRequestDto;
import com.estlc.fin_manager.dto.UserResponseDto;
import com.estlc.fin_manager.entities.Role;
import com.estlc.fin_manager.entities.User;
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
 * Mapper MapStruct de User.
 * Les relations (roles, accounts) sont resolues par le service.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "roles", target = "roleIds", qualifiedByName = "roleListToIdSet")
    UserResponseDto toDTO(User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    User toDTOEntity(UserRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    void updateEntityFromDto(UserRequestDto dto, @MappingTarget User user);

    List<UserResponseDto> toDTOList(List<User> users);

    List<User> toEntityList(List<UserRequestDto> dtos);

    @Named("roleListToIdSet")
    default Set<Long> roleListToIdSet(List<Role> roles) {
        if (roles == null) {
            return Set.of();
        }
        return roles.stream()
                .map(Role::getIdRole)
                .collect(Collectors.toSet());
    }
}
