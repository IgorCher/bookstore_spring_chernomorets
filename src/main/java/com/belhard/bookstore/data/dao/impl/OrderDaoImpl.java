package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderDao;
import com.belhard.bookstore.data.dto.OrderDto;
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
public class OrderDaoImpl implements OrderDao {
    private static final String FIND_BY_ID = "SELECT o.id, o.user_id, o.cost FROM orders o WHERE id = ?";
    private static final String FIND_ALL = "SELECT o.id, o.user_id, o.cost FROM orders o";
    private static final String FIND_BY_USER_ID = "SELECT o.id, o.user_id, o.cost FROM orders o WHERE user_id = ?";
    private static final String CREATE_N = "INSERT INTO orders (user_id, cost) VALUES (:userId, :cost)";
    private static final String UPDATE_N = "UPDATE orders SET user_id = :userId, cost = :cost WHERE id = :id";
    private static final String DELETE = "DELETE FROM orders WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public OrderDto findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, this::mapRow, id);
    }

    @Override
    public List<OrderDto> findAll() {
        return jdbcTemplate.query(FIND_ALL, this::mapRow);
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        return jdbcTemplate.query(FIND_BY_USER_ID, this::mapRow, userId);
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = prepareForUpdate(orderDto);
        SqlParameterSource namedParams = new MapSqlParameterSource().addValues(params);
        namedParameterJdbcTemplate.update(CREATE_N, namedParams, keyHolder, new String[]{"id"});
        long id = keyHolder.getKey().longValue();
        return findById(id);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Map<String, Object> params = prepareForUpdate(orderDto);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(params);
        namedParameterJdbcTemplate.update(UPDATE_N, namedParameters);
        return orderDto;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    private OrderDto mapRow(ResultSet rs, int num) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(rs.getLong("id"));
        orderDto.setUserId(rs.getLong("user_id"));
        orderDto.setTotalCost(rs.getBigDecimal("cost"));
        return orderDto;
    }

    private Map<String, Object> prepareForUpdate(OrderDto orderDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", orderDto.getId());
        params.put("userId", orderDto.getUserId());
        params.put("cost", orderDto.getTotalCost());
        return params;
    }
}
