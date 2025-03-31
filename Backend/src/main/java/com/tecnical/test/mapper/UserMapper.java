package com.tecnical.test.mapper;

import com.tecnical.test.dto.UserDTO;
import com.tecnical.test.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = PermissionMapper.class)
public interface UserMapper {
    UserDTO toUserDTO(User user);
    @Mapping(target = "rol.permissions", source = "rol.permissions")
    User toUser(UserDTO userDTO);
    List<UserDTO> toUserDTOList(List<User> userList);
}
