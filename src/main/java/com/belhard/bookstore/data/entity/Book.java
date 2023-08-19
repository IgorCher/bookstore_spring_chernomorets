package com.belhard.bookstore.data.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book {

    private long id;
    private String title;
    private String author;
    private String year;
    private BigDecimal price;
    private int pages;
    private String isbn;

    private Cover cover;

    public enum Cover {
        HARD,
        SOFT,
        OTHER
    }
}
