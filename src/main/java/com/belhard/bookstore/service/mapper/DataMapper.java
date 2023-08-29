package com.belhard.bookstore.service.mapper;

import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.dto.OrderItemDto;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.dto.UserDto;

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
