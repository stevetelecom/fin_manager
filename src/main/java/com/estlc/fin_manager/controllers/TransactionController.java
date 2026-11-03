package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.TransactionRequestDto;
import com.estlc.fin_manager.dto.TransactionResponseDto;
import com.estlc.fin_manager.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionResponseDto> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public TransactionResponseDto findById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto create(@RequestBody TransactionRequestDto dto) {
        return transactionService.create(dto);
    }

    @PutMapping("/{id}")
    public TransactionResponseDto update(@PathVariable Long id, @RequestBody TransactionRequestDto dto) {
        return transactionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
