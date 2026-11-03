package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Agence bancaire, localisée par ses coordonnées GPS.
 */
@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_ag")
    private Long numAg;

    // "Long" et "Latt" du tableau : longitude et latitude
    // ("long" étant un mot-clé Java, on utilise le nom complet)
    private Double longitude;

    private Double latitude;

    @NotBlank(message = "Le nom de l'agence est obligatoire")
    @Size(max = 150, message = "Le nom de l'agence ne doit pas depasser 150 caracteres")
    @Column(length = 150)
    private String nomAg;

    @Size(max = 20, message = "Le telephone de l'agence ne doit pas depasser 20 caracteres")
    @Column(length = 20)
    private String telAg;

    @Email(message = "L'email de l'agence doit etre valide")
    @Size(max = 150, message = "L'email de l'agence ne doit pas depasser 150 caracteres")
    @Column(length = 150)
    private String emailAg;

    // 1 Agence regroupe * Comptes
    @OneToMany(mappedBy = "agency")
    private List<Account> accounts = new ArrayList<>();
}
