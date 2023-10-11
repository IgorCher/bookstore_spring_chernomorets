package com.belhard.bookstore.data.converter;

import com.belhard.bookstore.data.entity.User;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<User.Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(User.Role role) {
        return switch(role){
            case ADMIN -> 1;
            case MANAGER -> 2;
            case CUSTOMER -> 3;
        };
    }

    @Override
    public User.Role convertToEntityAttribute(Integer integer) {
        return switch(integer){
            case 1 -> User.Role.ADMIN;
            case 2 -> User.Role.MANAGER;
            case 3 -> User.Role.CUSTOMER;
            default -> throw new IllegalArgumentException();
        };
    }
}
