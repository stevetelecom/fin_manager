package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.LoanRequestDto;
import com.estlc.fin_manager.dto.LoanResponseDto;
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
    public List<LoanResponseDto> findAll() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public LoanResponseDto findById(@PathVariable Long id) {
        return loanService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanResponseDto create(@RequestBody LoanRequestDto dto) {
        return loanService.create(dto);
    }

    @PutMapping("/{id}")
    public LoanResponseDto update(@PathVariable Long id, @RequestBody LoanRequestDto dto) {
        return loanService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }
}
