package com.estlc.fin_manager.repositories;

import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.utils.StatutCompteEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUser_IdUser(String idUser);

    List<Account> findByAgency_NumAg(Long numAg);

    List<Account> findByStatutCompte(StatutCompteEnum statutCompte);
}
