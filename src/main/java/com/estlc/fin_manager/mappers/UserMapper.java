package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.UserRequestDto;
import com.estlc.fin_manager.dto.UserResponseDto;
import com.estlc.fin_manager.entities.Role;
import com.estlc.fin_manager.entities.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Convertit un User en UserResponseDto et un UserRequestDto en User.
 * Les relations (rôles) sont résolues par le service, pas par le mapper.
 */
@Component
public class UserMapper {

    public UserResponseDto toResponseDto(User user) {
        Set<Long> roleIds = user.getRoles() == null
                ? Set.of()
                : user.getRoles().stream()
                        .map(Role::getIdRole)
                        .collect(Collectors.toSet());

        return new UserResponseDto(
                user.getIdUser(),
                user.getNomUser(),
                user.getPrenomUser(),
                user.getEmail(),
                user.getPhone(),
                user.getDateN(),
                user.getSexe(),
                user.getProfession(),
                user.getPassword(),
                roleIds
        );
    }

    public User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setIdUser(dto.idUser());
        apply(dto, user);
        return user;
    }

    /** Copie les champs simples (sans la clé primaire ni les relations). */
    public void apply(UserRequestDto dto, User user) {
        user.setNomUser(dto.nomUser());
        user.setPrenomUser(dto.prenomUser());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setDateN(dto.dateN());
        user.setSexe(dto.sexe());
        user.setProfession(dto.profession());
        if (dto.password() != null) {
            user.setPassword(dto.password());
        }
    }
}
