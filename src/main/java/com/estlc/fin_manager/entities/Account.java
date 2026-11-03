package com.estlc.fin_manager.entities;

import com.estlc.fin_manager.utils.StatutCompteEnum;
import com.estlc.fin_manager.utils.TypeCompteEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @Column(name = "num_compte")
    private Long numCompte;

    @NotNull(message = "Le solde est obligatoire")
    @PositiveOrZero(message = "Le solde ne peut pas etre negatif")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal solde = BigDecimal.ZERO;

    @Size(max = 150, message = "L'intitule du compte ne doit pas depasser 150 caracteres")
    @Column(length = 150)
    private String intituleCompte;

    @Enumerated(EnumType.STRING)
    private TypeCompteEnum typeCompte; // COURANT ou EPARGNE

    private LocalDate dateOuverture;

    @Enumerated(EnumType.STRING)
    private StatutCompteEnum statutCompte; // PENDING, ACTIVE, BLOQUE, INACTIVE

    // * Comptes appartiennent à 1 User
    @NotNull(message = "Le proprietaire du compte est obligatoire")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "cni") // la PK de User est la CNI
    private User user;

    // * Comptes sont rattachés à 1 Agence
    @ManyToOne
    @JoinColumn(name = "agency_id", referencedColumnName = "num_ag") // PK de Agency = num_ag
    private Agency agency;

    // 1 Compte possède * Transactions
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    // 1 Compte possède * Crédits
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();
}
