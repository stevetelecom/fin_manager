package com.estlc.fin_manager.repositories;

import com.estlc.fin_manager.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByAccount_NumCompte(Long numCompte);
}
