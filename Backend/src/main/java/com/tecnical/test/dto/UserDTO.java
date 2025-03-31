package com.tecnical.test.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UserDTO {
    private Integer idUser;
    private String name;
    private String email;
    private String password;
    private RolDTO rol;
}
