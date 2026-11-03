package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.TransactionRequestDto;
import com.estlc.fin_manager.dto.TransactionResponseDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponseDto toResponseDto(Transaction transaction) {
        Account account = transaction.getAccount();

        return new TransactionResponseDto(
                transaction.getNumTransac(),
                transaction.getTypeTransac(),
                transaction.getDateTransac(),
                transaction.getMontantTransac(),
                transaction.getStatutTransac(),
                account != null ? account.getNumCompte() : null
        );
    }

    public Transaction toEntity(TransactionRequestDto dto) {
        Transaction transaction = new Transaction();
        transaction.setNumTransac(dto.numTransac());
        apply(dto, transaction);
        return transaction;
    }

    public void apply(TransactionRequestDto dto, Transaction transaction) {
        transaction.setTypeTransac(dto.typeTransac());
        transaction.setDateTransac(dto.dateTransac());
        transaction.setMontantTransac(dto.montantTransac());
        transaction.setStatutTransac(dto.statutTransac());
    }
}
