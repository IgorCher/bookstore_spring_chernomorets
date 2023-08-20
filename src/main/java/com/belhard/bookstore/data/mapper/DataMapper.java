package com.belhard.bookstore.data.mapper;

import com.belhard.bookstore.data.dto.BookDto;
import com.belhard.bookstore.data.dto.OrderDto;
import com.belhard.bookstore.data.dto.OrderItemDto;
import com.belhard.bookstore.data.dto.UserDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;
import com.belhard.bookstore.data.entity.User;

public interface DataMapper {
    BookDto toDto(Book entity);

    UserDto toDto(User entity);

    OrderDto toDto(Order entity);

    OrderItemDto toDto(OrderItem entity);

    Book toEntity(BookDto dto);

    User toEntity(UserDto dto);

    Order toEntity(OrderDto dto);

    OrderItem toEntity(OrderItemDto dto);
}
