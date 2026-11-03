package com.estlc.fin_manager.services.implementations;

import com.estlc.fin_manager.dto.AgencyRequestDto;
import com.estlc.fin_manager.dto.AgencyResponseDto;
import com.estlc.fin_manager.entities.Agency;
import com.estlc.fin_manager.mappers.AgencyMapper;
import com.estlc.fin_manager.repositories.AgencyRepository;
import com.estlc.fin_manager.services.interfaces.AgencyServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AgencyService implements AgencyServiceInterface {

    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AgencyResponseDto> getAllAgencies() {
        return agencyRepository.findAll().stream()
                .map(agencyMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AgencyResponseDto getAgencyById(Long numAg) {
        return agencyMapper.toDTO(getAgency(numAg));
    }

    @Override
    @Transactional
    public AgencyResponseDto saveAgency(AgencyRequestDto dto) {
        Agency agency = agencyMapper.toDTOEntity(dto);
        return agencyMapper.toDTO(agencyRepository.save(agency));
    }

    @Override
    @Transactional
    public AgencyResponseDto updateAgency(Long numAg, AgencyRequestDto dto) {
        Agency agency = getAgency(numAg);
        agencyMapper.updateEntityFromDto(dto, agency);
        return agencyMapper.toDTO(agencyRepository.save(agency));
    }

    @Override
    @Transactional
    public void deleteAgency(Long numAg) {
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
