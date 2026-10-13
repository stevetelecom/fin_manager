package com.estlc.fin_manager.dto;

import java.time.LocalDate;
import java.util.Set;

/**
 * DTO de User : on expose les identifiants des rôles au lieu des objets Role complets.
 */
public record UserDto(
        Long idUser,
        String nomUser,
        String prenomUser,
        String email,
        String phone,
        LocalDate dateNaissance,
        String sexe,
        String profession,
        Set<Long> roleIds
) {
}
