package com.estlc.fin_manager.services.interfaces;

import com.estlc.fin_manager.dto.LoanRequestDto;
import com.estlc.fin_manager.dto.LoanResponseDto;

import java.util.List;

/**
 * Contrat metier de Loan.
 */
public interface LoanServiceInterface {

    LoanResponseDto saveLoan(LoanRequestDto loanRequestDto);

    LoanResponseDto updateLoan(Long idCredit, LoanRequestDto loanRequestDto);

    void deleteLoan(Long idCredit);

    List<LoanResponseDto> getAllLoans();

    LoanResponseDto getLoanById(Long idCredit);
}
