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

    @NotBlank(message = "Le nom de la permission est obligatoire")
    @Size(max = 100, message = "Le nom de la permission ne doit pas depasser 100 caracteres")
    @Column(nullable = false, unique = true, length = 100)
    private String nomPer;

    @Size(max = 255, message = "La description de la permission ne doit pas depasser 255 caracteres")
    @Column(length = 255)
    private String descriptionPer;

    // * Permissions <-> * Roles (côté inverse, géré par Role.permissions)
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>(); // une permission doit pouvoir voir la liste de ses rôles
}
