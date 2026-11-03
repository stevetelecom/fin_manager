package com.estlc.fin_manager.dto;

import com.estlc.fin_manager.utils.SexEnum;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO de User : on expose les identifiants des rôles au lieu des objets Role complets.
 */
public record UserRequestDto(
        String idUser,
        String nomUser,
        String prenomUser,
        String email,
        String phone,
        LocalDateTime dateN,
        SexEnum sexe,
        String profession,
        String password,
        Set<Long> roleIds
) {
}
