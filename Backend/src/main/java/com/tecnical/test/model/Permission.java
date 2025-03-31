package com.tecnical.test.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission", length = 6)
    Integer idPermision;
    @Column(name = "key_name", length = 3)
    private String keyName;
    @Column(length = 35, nullable = false)
    String name;
}
