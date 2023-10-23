package com.belhard.bookstore.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private Integer year;

    private BigDecimal price;

    private Integer pages;

    private String isbn;

    private Cover cover;

    private boolean deleted;

    @SuppressWarnings("unused")
    public enum Cover {
        HARD,
        SOFT,
        OTHER
    }
}
