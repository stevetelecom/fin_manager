package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.LoanRequestDto;
import com.estlc.fin_manager.dto.LoanResponseDto;
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
    public List<LoanResponseDto> findAll() {
        return loanRepository.findAll().stream()
                .map(loanMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public LoanResponseDto findById(Long idCredit) {
        return loanMapper.toDTO(getLoan(idCredit));
    }

    @Transactional
    public LoanResponseDto create(LoanRequestDto dto) {
        if (dto.accountId() == null) {
            throw new IllegalArgumentException("Le compte (accountId) est obligatoire");
        }

        Loan loan = loanMapper.toDTOEntity(dto);
        loan.setAccount(getAccount(dto.accountId()));
        return loanMapper.toDTO(loanRepository.save(loan));
    }

    @Transactional
    public LoanResponseDto update(Long idCredit, LoanRequestDto dto) {
        Loan loan = getLoan(idCredit);
        loanMapper.updateEntityFromDto(dto, loan);
        if (dto.accountId() != null) {
            loan.setAccount(getAccount(dto.accountId()));
        }
        return loanMapper.toDTO(loanRepository.save(loan));
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
