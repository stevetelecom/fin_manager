package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.AgencyDto;
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
    public List<AgencyDto> findAll() {
        return agencyRepository.findAll().stream()
                .map(agencyMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public AgencyDto findById(Long numAg) {
        return agencyMapper.toDto(getAgency(numAg));
    }

    @Transactional
    public AgencyDto create(AgencyDto dto) {
        Agency agency = agencyMapper.toEntity(dto);
        return agencyMapper.toDto(agencyRepository.save(agency));
    }

    @Transactional
    public AgencyDto update(Long numAg, AgencyDto dto) {
        Agency agency = getAgency(numAg);
        agencyMapper.apply(dto, agency);
        return agencyMapper.toDto(agencyRepository.save(agency));
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
