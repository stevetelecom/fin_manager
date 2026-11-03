package com.estlc.fin_manager.services.implementations;

import com.estlc.fin_manager.dto.LoanRequestDto;
import com.estlc.fin_manager.dto.LoanResponseDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Loan;
import com.estlc.fin_manager.mappers.LoanMapper;
import com.estlc.fin_manager.repositories.AccountRepository;
import com.estlc.fin_manager.repositories.LoanRepository;
import com.estlc.fin_manager.services.interfaces.LoanServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoanService implements LoanServiceInterface {

    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;
    private final LoanMapper loanMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LoanResponseDto> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(loanMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponseDto getLoanById(Long idCredit) {
        return loanMapper.toDTO(getLoan(idCredit));
    }

    @Override
    @Transactional
    public LoanResponseDto saveLoan(LoanRequestDto dto) {
        if (dto.accountId() == null) {
            throw new IllegalArgumentException("Le compte (accountId) est obligatoire");
        }

        Loan loan = loanMapper.toDTOEntity(dto);
        loan.setAccount(getAccount(dto.accountId()));
        return loanMapper.toDTO(loanRepository.save(loan));
    }

    @Override
    @Transactional
    public LoanResponseDto updateLoan(Long idCredit, LoanRequestDto dto) {
        Loan loan = getLoan(idCredit);
        loanMapper.updateEntityFromDto(dto, loan);
        if (dto.accountId() != null) {
            loan.setAccount(getAccount(dto.accountId()));
        }
        return loanMapper.toDTO(loanRepository.save(loan));
    }

    @Override
    @Transactional
    public void deleteLoan(Long idCredit) {
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
