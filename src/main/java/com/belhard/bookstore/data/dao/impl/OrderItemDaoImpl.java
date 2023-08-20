package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderItemDao;
import com.belhard.bookstore.data.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {
    private static final String FIND_BY_ID = "SELECT oi.id, oi.order_id, oi.book_id, oi.quantity, oi.price FROM order_items oi WHERE id = ?";
    private static final String FIND_ALL = "SELECT oi.id, oi.order_id, oi.book_id, oi.quantity, oi.price FROM order_items oi";
    private static final String FIND_BY_ORDER_ID = "SELECT oi.id, oi.order_id, oi.book_id, oi.quantity, oi.price FROM order_items oi WHERE order_id = ?";
    private static final String CREATE_N = "INSERT INTO order_items (order_id, book_id, quantity, price) VALUES (:orderId, :bookId, :quantity, :price)";
    private static final String UPDATE_N = "UPDATE order_items SET order_id = :orderId, book_id = :bookId, quantity = :quantity, price = :price WHERE id = :id";
    private static final String DELETE = "DELETE FROM order_items WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public OrderItemDto findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, this::mapRow, id);
    }

    @Override
    public List<OrderItemDto> findAll() {
        return jdbcTemplate.query(FIND_ALL, this::mapRow);
    }

    @Override
    public List<OrderItemDto> findByOrderId(Long orderId) {
        return jdbcTemplate.query(FIND_BY_ORDER_ID, this::mapRow, orderId);
    }

    @Override
    public OrderItemDto create(OrderItemDto orderItemDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = prepareForUpdate(orderItemDto);
        SqlParameterSource namedParams = new MapSqlParameterSource().addValues(params);
        namedParameterJdbcTemplate.update(CREATE_N, namedParams, keyHolder, new String[]{"id"});
        long id = keyHolder.getKey().longValue();
        return findById(id);
    }

    @Override
    public OrderItemDto update(OrderItemDto orderItemDto) {
        Map<String, Object> params = prepareForUpdate(orderItemDto);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(params);
        namedParameterJdbcTemplate.update(UPDATE_N, namedParameters);
        return orderItemDto;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    private OrderItemDto mapRow(ResultSet rs, int num) throws SQLException {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(rs.getLong("id"));
        orderItemDto.setOrderId(rs.getLong("order_id"));
        orderItemDto.setBookId(rs.getLong("book_id"));
        orderItemDto.setBookQuantity(rs.getInt("quantity"));
        orderItemDto.setBookPrice(rs.getBigDecimal("price"));
        return orderItemDto;
    }

    private static Map<String, Object> prepareForUpdate(OrderItemDto orderItemDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", orderItemDto.getId());
        params.put("orderId", orderItemDto.getOrderId());
        params.put("bookId", orderItemDto.getBookId());
        params.put("quantity", orderItemDto.getBookQuantity());
        params.put("price", orderItemDto.getBookPrice());
        return params;
    }
}
