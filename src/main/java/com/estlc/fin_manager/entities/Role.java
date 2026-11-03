package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Rôle d'un utilisateur (ADMIN, CLIENT, AGENT...), qui regroupe des permissions.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long idRole; // colonne role_id pour correspondre aux referencedColumnName des tables de jointure

    @NotBlank(message = "Le nom du role est obligatoire")
    @Size(max = 50, message = "Le nom du role ne doit pas depasser 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String nomRole;

    @Size(max = 255, message = "La description du role ne doit pas depasser 255 caracteres")
    @Column(length = 255)
    private String description;

    // * Roles <-> * Users (côté inverse, géré par User.roles)
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>(); // un rôle doit pouvoir voir la liste de ses utilisateurs

    // * Roles <-> * Permissions
    @ManyToMany
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id_per")
    )
    private List<Permission> permissions = new ArrayList<>(); // un rôle doit pouvoir voir la liste de ses permissions
}
