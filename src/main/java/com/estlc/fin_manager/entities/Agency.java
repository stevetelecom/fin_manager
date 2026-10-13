package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
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
    private Long numAg;

    // "Long" et "Latt" du tableau : longitude et latitude
    // ("long" étant un mot-clé Java, on utilise le nom complet)
    private Double longitude;

    private Double latitude;

    private String nomAg;

    private String telAg;

    private String emailAg;

    // 1 Agence regroupe * Comptes
    @OneToMany(mappedBy = "agency")
    private List<Account> accounts = new ArrayList<>();
}
