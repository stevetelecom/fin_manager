package com.estlc.fin_manager.controllers;

import jakarta.validation.Valid;

import com.estlc.fin_manager.dto.AccountRequestDto;
import com.estlc.fin_manager.dto.AccountResponseDto;
import com.estlc.fin_manager.services.interfaces.AccountServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceInterface accountService;

    @GetMapping
    public List<AccountResponseDto> findAll() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountResponseDto findById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto create(@Valid @RequestBody AccountRequestDto dto) {
        return accountService.saveAccount(dto);
    }

    @PutMapping("/{id}")
    public AccountResponseDto update(@PathVariable Long id, @Valid @RequestBody AccountRequestDto dto) {
        return accountService.updateAccount(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}
