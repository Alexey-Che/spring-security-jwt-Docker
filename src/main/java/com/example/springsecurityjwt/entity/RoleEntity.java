package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role_table")
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public RoleEntity(String role_user) {
        this.name = role_user;
    }

    public RoleEntity() {

    }
}
