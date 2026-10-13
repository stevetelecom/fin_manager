package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Compte bancaire d'un utilisateur, rattaché à une agence.
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numCompte;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal solde = BigDecimal.ZERO;

    private String intituleCompte;

    private String typeCompte;

    private LocalDate dateOuverture;

    private String statutCompte;

    // * Comptes appartiennent à 1 User
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    // * Comptes sont rattachés à 1 Agence
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    // 1 Compte possède * Transactions
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    // 1 Compte possède * Crédits
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();
}
