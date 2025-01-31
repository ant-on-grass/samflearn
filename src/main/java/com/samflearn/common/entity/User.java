package com.samflearn.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private Boolean status;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String name, String email, String password, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = true;
        this.userRole = userRole;
    }

    public User() {

    }
}
