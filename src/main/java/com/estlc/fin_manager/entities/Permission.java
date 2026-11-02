package com.estlc.fin_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "id_per")
    private Long idPer;

    @Column(nullable = false, unique = true)
    private String nomPer;

    private String descriptionPer;

    // * Permissions <-> * Roles (côté inverse, géré par Role.permissions)
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>(); // une permission doit pouvoir voir la liste de ses rôles
}
