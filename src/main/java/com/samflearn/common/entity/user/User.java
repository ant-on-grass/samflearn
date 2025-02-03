package com.samflearn.common.entity.user;

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

    private Boolean isDelete;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String name, String email, String password, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isDelete = false;
        this.userRole = userRole;
    }

    public User() {

    }

    public void updateStatus() {
        this.isDelete = !this.isDelete;
    }
}
