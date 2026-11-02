package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
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

    @Column(nullable = false, unique = true)
    private String nomRole;

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
