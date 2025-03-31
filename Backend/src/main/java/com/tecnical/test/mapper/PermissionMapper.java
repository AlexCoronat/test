package com.tecnical.test.mapper;

import com.tecnical.test.dto.PermissionDTO;
import com.tecnical.test.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {
    PermissionDTO toPermissinDTO(Permission permission);
    Permission toPermission(PermissionDTO permissionDTO);
    List<PermissionDTO> toPermissionDTOList(List<Permission> permissionList);
}
