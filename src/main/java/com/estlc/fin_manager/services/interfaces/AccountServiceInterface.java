package com.estlc.fin_manager.services.interfaces;

import com.estlc.fin_manager.dto.AccountRequestDto;
import com.estlc.fin_manager.dto.AccountResponseDto;

import java.util.List;

/**
 * Contrat metier de Account.
 */
public interface AccountServiceInterface {

    AccountResponseDto saveAccount(AccountRequestDto accountRequestDto);

    AccountResponseDto updateAccount(Long numCompte, AccountRequestDto accountRequestDto);

    void deleteAccount(Long numCompte);

    List<AccountResponseDto> getAllAccounts();

    AccountResponseDto getAccountById(Long numCompte);
}
