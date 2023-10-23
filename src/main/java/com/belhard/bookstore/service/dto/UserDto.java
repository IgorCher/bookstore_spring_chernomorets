package com.belhard.bookstore.service.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String login;

    private String password;

    private Role role;

    private boolean deleted;

    @SuppressWarnings("unused")
    public enum Role {
        ADMIN,
        MANAGER,
        CUSTOMER
    }
}
