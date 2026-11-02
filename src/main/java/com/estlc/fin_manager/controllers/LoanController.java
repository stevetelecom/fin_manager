package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.LoanDto;
import com.estlc.fin_manager.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public List<LoanDto> findAll() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public LoanDto findById(@PathVariable Long id) {
        return loanService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDto create(@RequestBody LoanDto dto) {
        return loanService.create(dto);
    }

    @PutMapping("/{id}")
    public LoanDto update(@PathVariable Long id, @RequestBody LoanDto dto) {
        return loanService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }
}
