package com.estlc.fin_manager.services.interfaces;

import com.estlc.fin_manager.dto.AgencyRequestDto;
import com.estlc.fin_manager.dto.AgencyResponseDto;

import java.util.List;

/**
 * Contrat metier de Agency.
 */
public interface AgencyServiceInterface {

    AgencyResponseDto saveAgency(AgencyRequestDto agencyRequestDto);

    AgencyResponseDto updateAgency(Long numAg, AgencyRequestDto agencyRequestDto);

    void deleteAgency(Long numAg);

    List<AgencyResponseDto> getAllAgencies();

    AgencyResponseDto getAgencyById(Long numAg);
}
