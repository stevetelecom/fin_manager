package com.estlc.fin_manager.dto;

import com.estlc.fin_manager.utils.SexEnum;

import java.time.LocalDate;
import java.util.Set;

/**
 * DTO de User : on expose les identifiants des rôles au lieu des objets Role complets.
 */
public record UserDto(
        String idUser,
        String nomUser,
        String prenomUser,
        String email,
        String phone,
        LocalDate dateNaissance,
        LocalDate dateInscription,
        SexEnum sexe,
        String profession,
        Set<Long> roleIds
) {
}
