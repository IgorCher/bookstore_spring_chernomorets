package com.belhard.bookstore.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private long id;
    private String title;
    private String author;
    private String year;
    private BigDecimal price;
    private int pages;
    private String isbn;
    private CoverDto coverDto;

    public enum CoverDto {
        HARD,
        SOFT,
        OTHER
    }
}
