package com.tecnical.test.mapper;

import com.tecnical.test.dto.RolDTO;
import com.tecnical.test.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",uses = PermissionMapper.class)
public interface RolMapper {
    RolDTO toRolDTO(Rol rol);
    Rol toRol(RolDTO rolDTO);
    List<RolDTO> toRolDTOList(List<Rol> rolList);
}
