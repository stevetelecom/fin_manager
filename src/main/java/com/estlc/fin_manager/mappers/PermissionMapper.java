package com.estlc.fin_manager.mappers;

import com.estlc.fin_manager.dto.PermissionDto;
import com.estlc.fin_manager.entities.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionDto toDto(Permission permission) {
        return new PermissionDto(
                permission.getIdPer(),
                permission.getNomPer(),
                permission.getDescriptionPer()
        );
    }

    public Permission toEntity(PermissionDto dto) {
        Permission permission = new Permission();
        permission.setIdPer(dto.idPer());
        apply(dto, permission);
        return permission;
    }

    public void apply(PermissionDto dto, Permission permission) {
        permission.setNomPer(dto.nomPer());
        permission.setDescriptionPer(dto.descriptionPer());
    }
}
