package com.estlc.fin_manager.dto;

import com.estlc.fin_manager.utils.SexEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO d'entree pour User.
 * L'idUser (CNI) est facultatif : il est genere par le serveur s'il est absent.
 */
public record UserRequestDto(
        String idUser,
        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 100, message = "Le nom ne doit pas depasser 100 caracteres")
        String nomUser,
        @Size(max = 100, message = "Le prenom ne doit pas depasser 100 caracteres")
        String prenomUser,
        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "L'email doit etre valide")
        @Size(max = 150, message = "L'email ne doit pas depasser 150 caracteres")
        String email,
        @NotBlank(message = "Le telephone est obligatoire")
        @Size(max = 20, message = "Le telephone ne doit pas depasser 20 caracteres")
        String phone,
        @NotNull(message = "La date de naissance est obligatoire")
        @Past(message = "La date de naissance doit etre dans le passe")
        LocalDateTime dateN,
        @NotNull(message = "Le sexe est obligatoire")
        SexEnum sexe,
        @NotBlank(message = "La profession est obligatoire")
        @Size(max = 100, message = "La profession ne doit pas depasser 100 caracteres")
        String profession,
        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(max = 255, message = "Le mot de passe ne doit pas depasser 255 caracteres")
        String password,
        Set<Long> roleIds
) {
}
