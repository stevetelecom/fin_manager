package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.PermissionRequestDto;
import com.estlc.fin_manager.dto.PermissionResponseDto;
import com.estlc.fin_manager.entities.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionResponseDto toResponseDto(Permission permission) {
        return new PermissionResponseDto(
                permission.getIdPer(),
                permission.getNomPer(),
                permission.getDescriptionPer()
        );
    }

    public Permission toEntity(PermissionRequestDto dto) {
        Permission permission = new Permission();
        permission.setIdPer(dto.idPer());
        apply(dto, permission);
        return permission;
    }

    public void apply(PermissionRequestDto dto, Permission permission) {
        permission.setNomPer(dto.nomPer());
        permission.setDescriptionPer(dto.descriptionPer());
    }
}
