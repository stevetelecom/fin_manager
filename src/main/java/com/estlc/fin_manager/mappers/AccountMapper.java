package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.AccountRequestDto;
import com.estlc.fin_manager.dto.AccountResponseDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Agency;
import com.estlc.fin_manager.entities.User;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDto toResponseDto(Account account) {
        User user = account.getUser();
        Agency agency = account.getAgency();

        return new AccountResponseDto(
                account.getNumCompte(),
                account.getSolde(),
                account.getIntituleCompte(),
                account.getTypeCompte(),
                account.getDateOuverture(),
                account.getStatutCompte(),
                user != null ? user.getIdUser() : null,
                agency != null ? agency.getNumAg() : null
        );
    }

    public Account toEntity(AccountRequestDto dto) {
        Account account = new Account();
        account.setNumCompte(dto.numCompte());
        apply(dto, account);
        return account;
    }

    public void apply(AccountRequestDto dto, Account account) {
        if (dto.solde() != null) {
            account.setSolde(dto.solde());
        }
        account.setIntituleCompte(dto.intituleCompte());
        account.setTypeCompte(dto.typeCompte());
        account.setDateOuverture(dto.dateOuverture());
        account.setStatutCompte(dto.statutCompte());
    }
}
