package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.LoanRequestDto;
import com.estlc.fin_manager.dto.LoanResponseDto;
import com.estlc.fin_manager.entities.Account;
import com.estlc.fin_manager.entities.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanResponseDto toResponseDto(Loan loan) {
        Account account = loan.getAccount();

        return new LoanResponseDto(
                loan.getIdCredit(),
                loan.getMontantCredit(),
                loan.getDateCredit(),
                loan.getTypeCredit(),
                loan.getMotifCredit(),
                account != null ? account.getNumCompte() : null
        );
    }

    public Loan toEntity(LoanRequestDto dto) {
        Loan loan = new Loan();
        loan.setIdCredit(dto.idCredit());
        apply(dto, loan);
        return loan;
    }

    public void apply(LoanRequestDto dto, Loan loan) {
        loan.setMontantCredit(dto.montantCredit());
        loan.setDateCredit(dto.dateCredit());
        loan.setTypeCredit(dto.typeCredit());
        loan.setMotifCredit(dto.motifCredit());
    }
}
