package com.estlc.fin_manager.controllers;

import jakarta.validation.Valid;

import com.estlc.fin_manager.dto.AgencyRequestDto;
import com.estlc.fin_manager.dto.AgencyResponseDto;
import com.estlc.fin_manager.services.interfaces.AgencyServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agencies")
@RequiredArgsConstructor
public class AgencyController {

    private final AgencyServiceInterface agencyService;

    @GetMapping
    public List<AgencyResponseDto> findAll() {
        return agencyService.getAllAgencies();
    }

    @GetMapping("/{id}")
    public AgencyResponseDto findById(@PathVariable Long id) {
        return agencyService.getAgencyById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgencyResponseDto create(@Valid @RequestBody AgencyRequestDto dto) {
        return agencyService.saveAgency(dto);
    }

    @PutMapping("/{id}")
    public AgencyResponseDto update(@PathVariable Long id, @Valid @RequestBody AgencyRequestDto dto) {
        return agencyService.updateAgency(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        agencyService.deleteAgency(id);
    }
}
