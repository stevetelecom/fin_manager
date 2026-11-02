package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.LoanDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Loan;
import com.estlc.fin_manager.mappers.LoanMapper;
import com.estlc.fin_manager.repositories.AccountRepository;
import com.estlc.fin_manager.repositories.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;
    private final LoanMapper loanMapper;

    @Transactional(readOnly = true)
    public List<LoanDto> findAll() {
        return loanRepository.findAll().stream()
                .map(loanMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public LoanDto findById(Long idCredit) {
        return loanMapper.toDto(getLoan(idCredit));
    }

    @Transactional
    public LoanDto create(LoanDto dto) {
        if (dto.accountId() == null) {
            throw new IllegalArgumentException("Le compte (accountId) est obligatoire");
        }

        Loan loan = loanMapper.toEntity(dto);
        loan.setAccount(getAccount(dto.accountId()));
        return loanMapper.toDto(loanRepository.save(loan));
    }

    @Transactional
    public LoanDto update(Long idCredit, LoanDto dto) {
        Loan loan = getLoan(idCredit);
        loanMapper.apply(dto, loan);
        if (dto.accountId() != null) {
            loan.setAccount(getAccount(dto.accountId()));
        }
        return loanMapper.toDto(loanRepository.save(loan));
    }

    @Transactional
    public void delete(Long idCredit) {
        if (!loanRepository.existsById(idCredit)) {
            throw new NoSuchElementException("Crédit introuvable : " + idCredit);
        }
        loanRepository.deleteById(idCredit);
    }

    private Loan getLoan(Long idCredit) {
        return loanRepository.findById(idCredit)
                .orElseThrow(() -> new NoSuchElementException("Crédit introuvable : " + idCredit));
    }

    private Account getAccount(Long numCompte) {
        return accountRepository.findById(numCompte)
                .orElseThrow(() -> new NoSuchElementException("Compte introuvable : " + numCompte));
    }
}
