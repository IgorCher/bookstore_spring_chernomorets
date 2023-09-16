package com.belhard.bookstore.data.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "year")
    private int year;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "pages")
    private int pages;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "cover_type_id")
    private Cover cover;

    public enum Cover {
        HARD,
        SOFT,
        OTHER
    }
}
