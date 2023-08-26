package com.belhard.bookstore.data.entity;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
   @Column(name = "role_id")
    private Role role;

    public enum Role {
        ADMIN,
        MANAGER,
        CUSTOMER
    }
}
