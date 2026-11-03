package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.AgencyRequestDto;
import com.estlc.fin_manager.dto.AgencyResponseDto;
import com.estlc.fin_manager.entities.Agency;
import com.estlc.fin_manager.mappers.AgencyMapper;
import com.estlc.fin_manager.repositories.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AgencyService {

    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;

    @Transactional(readOnly = true)
    public List<AgencyResponseDto> findAll() {
        return agencyRepository.findAll().stream()
                .map(agencyMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AgencyResponseDto findById(Long numAg) {
        return agencyMapper.toDTO(getAgency(numAg));
    }

    @Transactional
    public AgencyResponseDto create(AgencyRequestDto dto) {
        Agency agency = agencyMapper.toDTOEntity(dto);
        return agencyMapper.toDTO(agencyRepository.save(agency));
    }

    @Transactional
    public AgencyResponseDto update(Long numAg, AgencyRequestDto dto) {
        Agency agency = getAgency(numAg);
        agencyMapper.updateEntityFromDto(dto, agency);
        return agencyMapper.toDTO(agencyRepository.save(agency));
    }

    @Transactional
    public void delete(Long numAg) {
        if (!agencyRepository.existsById(numAg)) {
            throw new NoSuchElementException("Agence introuvable : " + numAg);
        }
        agencyRepository.deleteById(numAg);
    }

    private Agency getAgency(Long numAg) {
        return agencyRepository.findById(numAg)
                .orElseThrow(() -> new NoSuchElementException("Agence introuvable : " + numAg));
    }
}
