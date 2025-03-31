package com.tecnical.test.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RolDTO {
    private Integer idRol;
    private String name;
    private Set<PermissionDTO> permissions = new HashSet<>();
}
