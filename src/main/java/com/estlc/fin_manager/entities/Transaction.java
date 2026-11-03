package com.estlc.fin_manager.entities;

import com.estlc.fin_manager.utils.StatutTransactionEnum;
import com.estlc.fin_manager.utils.TypeTransactionEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Opération effectuée sur un compte (dépôt, retrait, virement...).
 */
@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_transac")
    private Long numTransac;

    @Enumerated(EnumType.STRING)
    private TypeTransactionEnum typeTransac; // DEPOT, RETRAIT, VIREMENT

    private LocalDateTime dateTransac;

    @NotNull(message = "Le montant de la transaction est obligatoire")
    @Positive(message = "Le montant de la transaction doit etre positif")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal montantTransac;

    @Enumerated(EnumType.STRING)
    private StatutTransactionEnum statutTransac; // PENDING, VALIDEE, ANNULEE, ECHOUEE

    // * Transactions concernent 1 Compte
    @NotNull(message = "Le compte de la transaction est obligatoire")
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "num_compte") // PK de Account = num_compte
    private Account account;

    // 1 Transaction génère 0..1 Notification
    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Notification notification;
}

// type et status enumerer , chacun doit avoir un client htttp , posmant ou thumdirburd