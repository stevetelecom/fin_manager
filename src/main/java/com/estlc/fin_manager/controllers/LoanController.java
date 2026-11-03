package com.estlc.fin_manager.controllers;

import jakarta.validation.Valid;

import com.estlc.fin_manager.dto.LoanRequestDto;
import com.estlc.fin_manager.dto.LoanResponseDto;
import com.estlc.fin_manager.services.interfaces.LoanServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanServiceInterface loanService;

    @GetMapping
    public List<LoanResponseDto> findAll() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public LoanResponseDto findById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanResponseDto create(@Valid @RequestBody LoanRequestDto dto) {
        return loanService.saveLoan(dto);
    }

    @PutMapping("/{id}")
    public LoanResponseDto update(@PathVariable Long id, @Valid @RequestBody LoanRequestDto dto) {
        return loanService.updateLoan(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        loanService.deleteLoan(id);
    }
}
