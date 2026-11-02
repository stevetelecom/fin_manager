package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.LoanDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanDto toDto(Loan loan) {
        Account account = loan.getAccount();

        return new LoanDto(
                loan.getIdCredit(),
                loan.getMontantCredit(),
                loan.getDateCredit(),
                loan.getTypeCredit(),
                loan.getMotifCredit(),
                account != null ? account.getNumCompte() : null
        );
    }

    public Loan toEntity(LoanDto dto) {
        Loan loan = new Loan();
        loan.setIdCredit(dto.idCredit());
        apply(dto, loan);
        return loan;
    }

    public void apply(LoanDto dto, Loan loan) {
        loan.setMontantCredit(dto.montantCredit());
        loan.setDateCredit(dto.dateCredit());
        loan.setTypeCredit(dto.typeCredit());
        loan.setMotifCredit(dto.motifCredit());
    }
}
