package com.estlc.fin_manager.repositories;

import com.estlc.fin_manager.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByNomPer(String nomPer);
}
