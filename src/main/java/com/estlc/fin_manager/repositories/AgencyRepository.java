package com.estlc.fin_manager.repositories;

import com.estlc.fin_manager.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    List<Agency> findByNomAgContainingIgnoreCase(String nomAg);
}
