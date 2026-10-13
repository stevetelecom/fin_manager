package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
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
    private Long numTransac;

    private String typeTransac;

    private LocalDateTime dateTransac;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal montantTransac;

    private String statutTransac;

    // * Transactions concernent 1 Compte
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private Account account;

    // 1 Transaction génère 0..1 Notification
    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Notification notification;
}
