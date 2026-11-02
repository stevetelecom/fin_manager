package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.TransactionDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto toDto(Transaction transaction) {
        Account account = transaction.getAccount();

        return new TransactionDto(
                transaction.getNumTransac(),
                transaction.getTypeTransac(),
                transaction.getDateTransac(),
                transaction.getMontantTransac(),
                transaction.getStatutTransac(),
                account != null ? account.getNumCompte() : null
        );
    }

    public Transaction toEntity(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setNumTransac(dto.numTransac());
        apply(dto, transaction);
        return transaction;
    }

    public void apply(TransactionDto dto, Transaction transaction) {
        transaction.setTypeTransac(dto.typeTransac());
        transaction.setDateTransac(dto.dateTransac());
        transaction.setMontantTransac(dto.montantTransac());
        transaction.setStatutTransac(dto.statutTransac());
    }
}
