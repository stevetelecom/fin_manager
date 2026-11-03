package com.estlc.fin_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO d'entree pour Agency.
 */
public record AgencyRequestDto(
        Long numAg,
        @NotNull(message = "La longitude est obligatoire")
        Double longitude,
        @NotNull(message = "La latitude est obligatoire")
        Double latitude,
        @NotBlank(message = "Le nom de l'agence est obligatoire")
        @Size(max = 150, message = "Le nom de l'agence ne doit pas depasser 150 caracteres")
        String nomAg,
        @NotBlank(message = "Le telephone de l'agence est obligatoire")
        @Size(max = 20, message = "Le telephone de l'agence ne doit pas depasser 20 caracteres")
        String telAg,
        @NotBlank(message = "L'email de l'agence est obligatoire")
        @Email(message = "L'email de l'agence doit etre valide")
        @Size(max = 150, message = "L'email de l'agence ne doit pas depasser 150 caracteres")
        String emailAg
) {
}
