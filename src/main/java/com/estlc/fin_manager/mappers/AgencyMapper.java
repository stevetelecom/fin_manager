package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.AgencyDto;
import com.estlc.fin_manager.entities.Agency;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapper {

    public AgencyDto toDto(Agency agency) {
        return new AgencyDto(
                agency.getNumAg(),
                agency.getLongitude(),
                agency.getLatitude(),
                agency.getNomAg(),
                agency.getTelAg(),
                agency.getEmailAg()
        );
    }

    public Agency toEntity(AgencyDto dto) {
        Agency agency = new Agency();
        agency.setNumAg(dto.numAg());
        apply(dto, agency);
        return agency;
    }

    public void apply(AgencyDto dto, Agency agency) {
        agency.setLongitude(dto.longitude());
        agency.setLatitude(dto.latitude());
        agency.setNomAg(dto.nomAg());
        agency.setTelAg(dto.telAg());
        agency.setEmailAg(dto.emailAg());
    }
}
