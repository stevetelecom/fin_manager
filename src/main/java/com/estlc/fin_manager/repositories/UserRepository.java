package com.estlc.fin_manager.repositories;

import com.estlc.fin_manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository de l'entité User.
 * La clé primaire est la CNI (String).
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByNomUserContainingIgnoreCase(String nom);
}
