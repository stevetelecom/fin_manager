package com.estlc.fin_manager.services.implementations;

import com.estlc.fin_manager.dto.TransactionRequestDto;
import com.estlc.fin_manager.dto.TransactionResponseDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Transaction;
import com.estlc.fin_manager.mappers.TransactionMapper;
import com.estlc.fin_manager.repositories.AccountRepository;
import com.estlc.fin_manager.repositories.TransactionRepository;
import com.estlc.fin_manager.services.interfaces.TransactionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionServiceInterface {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponseDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponseDto getTransactionById(Long numTransac) {
        return transactionMapper.toDTO(getTransaction(numTransac));
    }

    @Override
    @Transactional
    public TransactionResponseDto saveTransaction(TransactionRequestDto dto) {
        if (dto.accountId() == null) {
            throw new IllegalArgumentException("Le compte (accountId) est obligatoire");
        }

        Transaction transaction = transactionMapper.toDTOEntity(dto);
        transaction.setAccount(getAccount(dto.accountId()));
        return transactionMapper.toDTO(transactionRepository.save(transaction));
    }

    @Override
    @Transactional
    public TransactionResponseDto updateTransaction(Long numTransac, TransactionRequestDto dto) {
        Transaction transaction = getTransaction(numTransac);
        transactionMapper.updateEntityFromDto(dto, transaction);
        if (dto.accountId() != null) {
            transaction.setAccount(getAccount(dto.accountId()));
        }
        return transactionMapper.toDTO(transactionRepository.save(transaction));
    }

    @Override
    @Transactional
    public void deleteTransaction(Long numTransac) {
        if (!transactionRepository.existsById(numTransac)) {
            throw new NoSuchElementException("Transaction introuvable : " + numTransac);
        }
        transactionRepository.deleteById(numTransac);
    }

    private Transaction getTransaction(Long numTransac) {
        return transactionRepository.findById(numTransac)
                .orElseThrow(() -> new NoSuchElementException("Transaction introuvable : " + numTransac));
    }

    private Account getAccount(Long numCompte) {
        return accountRepository.findById(numCompte)
                .orElseThrow(() -> new NoSuchElementException("Compte introuvable : " + numCompte));
    }
}
