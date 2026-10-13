package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    private Long idRole;

    @Column(nullable = false, unique = true)
    private String nomRole;

    private String description;

    // * Roles <-> * Users (côté inverse, géré par User.roles)
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    // * Roles <-> * Permissions
    @ManyToMany
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();
}
