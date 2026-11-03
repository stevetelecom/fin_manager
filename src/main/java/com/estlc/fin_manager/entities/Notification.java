package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "id_notification")
    private Long idNotification;

    @Size(max = 50, message = "Le type de notification ne doit pas depasser 50 caracteres")
    @Column(length = 50)
    private String typeNot;

    @Size(max = 1000, message = "Le contenu de la notification ne doit pas depasser 1000 caracteres")
    @Column(length = 1000)
    private String contenuNot;

    private LocalDateTime dateNot;

    // Chaque Notification est liée à 1 Transaction (côté propriétaire de la relation)
    @NotNull(message = "La transaction de la notification est obligatoire")
    @OneToOne(optional = false)
    @JoinColumn(name = "transaction_id", referencedColumnName = "num_transac", unique = true) // PK de Transaction = num_transac
    private Transaction transaction;
}
