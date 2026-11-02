package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.UserDto;
import com.estlc.fin_manager.entities.Role;
import com.estlc.fin_manager.entities.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Convertit un User en UserDto et inversement.
 * Les relations (rôles) sont résolues par le service, pas par le mapper.
 */
@Component
public class UserMapper {

    public UserDto toDto(User user) {
        Set<Long> roleIds = user.getRoles() == null
                ? Set.of()
                : user.getRoles().stream()
                        .map(Role::getIdRole)
                        .collect(Collectors.toSet());

        return new UserDto(
                user.getIdUser(),
                user.getNomUser(),
                user.getPrenomUser(),
                user.getEmail(),
                user.getPhone(),
                user.getDateNaissance(),
                user.getDateInscription(),
                user.getSexe(),
                user.getProfession(),
                roleIds
        );
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setIdUser(dto.idUser());
        apply(dto, user);
        return user;
    }

    /** Copie les champs simples (sans la clé primaire ni les relations). */
    public void apply(UserDto dto, User user) {
        user.setNomUser(dto.nomUser());
        user.setPrenomUser(dto.prenomUser());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setDateNaissance(dto.dateNaissance());
        user.setDateInscription(dto.dateInscription());
        user.setSexe(dto.sexe());
        user.setProfession(dto.profession());
    }
}
