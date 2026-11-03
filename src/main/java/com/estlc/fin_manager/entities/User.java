package com.estlc.fin_manager.entities;

import com.estlc.fin_manager.utils.SexEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne doit pas depasser 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nomUser;

    @Size(max = 100, message = "Le prenom ne doit pas depasser 100 caracteres")
    @Column(length = 100)
    private String prenomUser; // user a un champ Date

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit etre valide")
    @Size(max = 150, message = "L'email ne doit pas depasser 150 caracteres")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Size(max = 20, message = "Le telephone ne doit pas depasser 20 caracteres")
    @Column(length = 20)
    private String phone;

    @Past(message = "La date de naissance doit etre dans le passe")
    private LocalDateTime dateN; // unique champ date de l'utilisateur

    @Enumerated(EnumType.STRING)
    private SexEnum sexe; // enum partagé depuis le dossier utils

    @Size(max = 100, message = "La profession ne doit pas depasser 100 caracteres")
    @Column(length = 100)
    private String profession;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(max = 255, message = "Le mot de passe ne doit pas depasser 255 caracteres")
    @Column(nullable = false, length = 255)
    private String password;

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
