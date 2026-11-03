package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.AccountRequestDto;
import com.estlc.fin_manager.dto.AccountResponseDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Agency;
import com.estlc.fin_manager.entities.User;
import com.estlc.fin_manager.mappers.AccountMapper;
import com.estlc.fin_manager.repositories.AccountRepository;
import com.estlc.fin_manager.repositories.AgencyRepository;
import com.estlc.fin_manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AgencyRepository agencyRepository;
    private final AccountMapper accountMapper;

    @Transactional(readOnly = true)
    public List<AccountResponseDto> findAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AccountResponseDto findById(Long numCompte) {
        return accountMapper.toDTO(getAccount(numCompte));
    }

    @Transactional
    public AccountResponseDto create(AccountRequestDto dto) {
        if (dto.userId() == null || dto.userId().isBlank()) {
            throw new IllegalArgumentException("Le userId (CNI du propriétaire) est obligatoire");
        }

        Account account = accountMapper.toDTOEntity(dto);
        account.setUser(getUser(dto.userId()));
        account.setAgency(resolveAgency(dto.agencyId()));
        return accountMapper.toDTO(accountRepository.save(account));
    }

    @Transactional
    public AccountResponseDto update(Long numCompte, AccountRequestDto dto) {
        Account account = getAccount(numCompte);
        accountMapper.updateEntityFromDto(dto, account);
        if (dto.userId() != null) {
            account.setUser(getUser(dto.userId()));
        }
        if (dto.agencyId() != null) {
            account.setAgency(resolveAgency(dto.agencyId()));
        }
        return accountMapper.toDTO(accountRepository.save(account));
    }

    @Transactional
    public void delete(Long numCompte) {
        if (!accountRepository.existsById(numCompte)) {
            throw new NoSuchElementException("Compte introuvable : " + numCompte);
        }
        accountRepository.deleteById(numCompte);
    }

    private Account getAccount(Long numCompte) {
        return accountRepository.findById(numCompte)
                .orElseThrow(() -> new NoSuchElementException("Compte introuvable : " + numCompte));
    }

    private User getUser(String idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur introuvable : " + idUser));
    }

    private Agency resolveAgency(Long numAg) {
        if (numAg == null) {
            return null;
        }
        return agencyRepository.findById(numAg)
                .orElseThrow(() -> new NoSuchElementException("Agence introuvable : " + numAg));
    }
}
