package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Crédit (prêt) accordé sur un compte.
 * La classe s'appelle Loan (comme au tableau), la table "Crédit" du diagramme.
 */
@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit")
    private Long idCredit;

    @NotNull(message = "Le montant du credit est obligatoire")
    @Positive(message = "Le montant du credit doit etre positif")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal montantCredit;

    private LocalDate dateCredit;

    private String typeCredit;

    private String motifCredit;

    // * Crédits sont rattachés à 1 Compte
    @NotNull(message = "Le compte du credit est obligatoire")
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "num_compte") // PK de Account = num_compte
    private Account account;
}
