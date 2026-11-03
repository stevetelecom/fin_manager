package com.estlc.fin_manager.entities;

import com.estlc.fin_manager.utils.SexEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilisateur de l'application (client, agent, administrateur...).
 * Table "users" car "user" est un mot réservé dans plusieurs SGBD (PostgreSQL).
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor // constructeur sans arguments (Lombok) ; ajouter @RequiredArgsConstructor si besoin
public class User {

    @Id
    @Column(name = "cni", nullable = false, unique = true)
    private String idUser; // CNI de l'utilisateur : clé primaire (String)

    @Column(nullable = false) // @Basic(optionel = false)
    private String nomUser;

    private String prenomUser; // user a un champ Date

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private LocalDateTime dateN; // unique champ date de l'utilisateur

    @Enumerated(EnumType.STRING)
    private SexEnum sexe; // enum partagé depuis le dossier utils

    private String profession;

    // 1 User possède * Comptes
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>(); // un user doit avoir la liste de ses comptes

    // * User <-> * Role
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "cni"), // la PK de User est la colonne cni
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private List<Role> roles = new ArrayList<>(); // un user doit pouvoir voir la liste de ses rôles
}
