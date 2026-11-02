package com.estlc.fin_manager.repositories;

import com.estlc.fin_manager.entities.Transaction;
import com.estlc.fin_manager.utils.StatutTransactionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount_NumCompte(Long numCompte);

    List<Transaction> findByStatutTransac(StatutTransactionEnum statutTransac);
}
