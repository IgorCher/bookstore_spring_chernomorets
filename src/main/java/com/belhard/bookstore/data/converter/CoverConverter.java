package com.belhard.bookstore.data.converter;

import com.belhard.bookstore.data.entity.Book;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CoverConverter implements AttributeConverter<Book.Cover,Integer> {
    @Override
    public Integer convertToDatabaseColumn(Book.Cover cover) {
        return switch(cover){
            case SOFT -> 1;
            case HARD -> 2;
            case OTHER -> 3;
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public Book.Cover convertToEntityAttribute(Integer integer) {
        return switch (integer){
            case 1 -> Book.Cover.SOFT;
            case 2 -> Book.Cover.HARD;
            case 3 -> Book.Cover.OTHER;
            default -> throw new IllegalArgumentException();
        };
    }
}
