package com.estlc.fin_manager.services.interfaces;

import com.estlc.fin_manager.dto.TransactionRequestDto;
import com.estlc.fin_manager.dto.TransactionResponseDto;

import java.util.List;

/**
 * Contrat metier de Transaction.
 */
public interface TransactionServiceInterface {

    TransactionResponseDto saveTransaction(TransactionRequestDto transactionRequestDto);

    TransactionResponseDto updateTransaction(Long numTransac, TransactionRequestDto transactionRequestDto);

    void deleteTransaction(Long numTransac);

    List<TransactionResponseDto> getAllTransactions();

    TransactionResponseDto getTransactionById(Long numTransac);
}
