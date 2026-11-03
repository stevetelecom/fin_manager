package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.AgencyRequestDto;
import com.estlc.fin_manager.dto.AgencyResponseDto;
import com.estlc.fin_manager.services.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
@RequiredArgsConstructor
public class AgencyController {

    private final AgencyService agencyService;

    @GetMapping
    public List<AgencyResponseDto> findAll() {
        return agencyService.findAll();
    }

    @GetMapping("/{id}")
    public AgencyResponseDto findById(@PathVariable Long id) {
        return agencyService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgencyResponseDto create(@RequestBody AgencyRequestDto dto) {
        return agencyService.create(dto);
    }

    @PutMapping("/{id}")
    public AgencyResponseDto update(@PathVariable Long id, @RequestBody AgencyRequestDto dto) {
        return agencyService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        agencyService.delete(id);
    }
}
