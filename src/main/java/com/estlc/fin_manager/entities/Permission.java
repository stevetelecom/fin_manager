package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Permission (droit d'action) pouvant être attribuée à plusieurs rôles.
 */
@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPer;

    @Column(nullable = false, unique = true)
    private String nomPer;

    private String descriptionPer;

    // * Permissions <-> * Roles (côté inverse, géré par Role.permissions)
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();
}
