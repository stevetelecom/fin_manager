package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.AccountRequestDto;
import com.estlc.fin_manager.dto.AccountResponseDto;
import com.estlc.fin_manager.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<AccountResponseDto> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountResponseDto findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto create(@RequestBody AccountRequestDto dto) {
        return accountService.create(dto);
    }

    @PutMapping("/{id}")
    public AccountResponseDto update(@PathVariable Long id, @RequestBody AccountRequestDto dto) {
        return accountService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
