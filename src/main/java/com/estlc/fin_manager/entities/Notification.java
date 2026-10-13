package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Notification envoyée suite à une transaction.
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    private String typeNot;

    @Column(length = 1000)
    private String contenuNot;

    private LocalDateTime dateNot;

    // Chaque Notification est liée à 1 Transaction (côté propriétaire de la relation)
    @OneToOne(optional = false)
    @JoinColumn(name = "transaction_id", unique = true)
    private Transaction transaction;
}
