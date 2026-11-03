package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.AgencyRequestDto;
import com.estlc.fin_manager.dto.AgencyResponseDto;
import com.estlc.fin_manager.entities.Agency;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapper {

    public AgencyResponseDto toResponseDto(Agency agency) {
        return new AgencyResponseDto(
                agency.getNumAg(),
                agency.getLongitude(),
                agency.getLatitude(),
                agency.getNomAg(),
                agency.getTelAg(),
                agency.getEmailAg()
        );
    }

    public Agency toEntity(AgencyRequestDto dto) {
        Agency agency = new Agency();
        agency.setNumAg(dto.numAg());
        apply(dto, agency);
        return agency;
    }

    public void apply(AgencyRequestDto dto, Agency agency) {
        agency.setLongitude(dto.longitude());
        agency.setLatitude(dto.latitude());
        agency.setNomAg(dto.nomAg());
        agency.setTelAg(dto.telAg());
        agency.setEmailAg(dto.emailAg());
    }
}
