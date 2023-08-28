package com.belhard.bookstore.service.mapper;

import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.dto.OrderItemDto;
import com.belhard.bookstore.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataMapperImpl implements DataMapper {
    @Override
    public BookDto toDto(Book entity) {
        BookDto bookDto = new BookDto();
        bookDto.setId(entity.getId());
        bookDto.setAuthor(entity.getAuthor());
        bookDto.setTitle(entity.getTitle());
        bookDto.setYear(entity.getYear());
        bookDto.setPrice(entity.getPrice());
        bookDto.setPages(entity.getPages());
        bookDto.setIsbn(entity.getIsbn());
        bookDto.setCoverDto(BookDto.CoverDto.valueOf(entity.getCover().toString()));
        return bookDto;
    }

    @Override
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        userDto.setLastName(entity.getLastName());
        userDto.setEmail(entity.getEmail());
        userDto.setLogin(entity.getLogin());
        userDto.setPassword(entity.getPassword());
        userDto.setRoleDto(UserDto.RoleDto.valueOf(entity.getRole().toString()));
        return userDto;
    }

    @Override
    public OrderDto toDto(Order entity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setUserDto(this.toDto(entity.getUser()));
        orderDto.setTotalCost(entity.getTotalCost());
        List<OrderItemDto> items = new ArrayList<>();
        entity.getOrderItems().forEach(orderItem -> {
            OrderItemDto orderItemDto = this.toDto(orderItem);
            items.add(orderItemDto);
        });
        orderDto.setOrderItemsDto(items);
        return orderDto;
    }

    @Override
    public OrderItemDto toDto(OrderItem entity) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(entity.getId());
        orderItemDto.setBookDto(this.toDto(entity.getBook()));
        orderItemDto.setBookQuantity(entity.getBookQuantity());
        orderItemDto.setBookPrice(entity.getBookPrice());
        return orderItemDto;
    }

    @Override
    public Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setYear(dto.getYear());
        book.setPrice(dto.getPrice());
        book.setPages(dto.getPages());
        book.setIsbn(dto.getIsbn());
        book.setCover(Book.Cover.valueOf(dto.getCoverDto().toString()));
        return book;
    }

    @Override
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRole(User.Role.valueOf(dto.getRoleDto().toString()));
        return user;
    }

    @Override
    public Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(this.toEntity(dto.getUserDto()));
        order.setTotalCost(dto.getTotalCost());
        List<OrderItem> items = new ArrayList<>();
        dto.getOrderItemsDto().forEach(orderItemDto -> {
            OrderItem orderItem = this.toEntity(orderItemDto);
            items.add(orderItem);
        });
        order.setOrderItems(items);
        return order;
    }

    @Override
    public OrderItem toEntity(OrderItemDto dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(dto.getId());
        orderItem.setBook(this.toEntity(dto.getBookDto()));
        orderItem.setBookQuantity(dto.getBookQuantity());
        orderItem.setBookPrice(dto.getBookPrice());
        return orderItem;
    }
}
