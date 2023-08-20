package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private static final String FIND_ALL = "SELECT b.id, b.author, b.title, b.year, b.price, b.pages, b.isbn, c.cover_type FROM books b JOIN cover_types c ON b.cover_type_id = c.id ORDER BY b.id";
    private static final String FIND_BY_ID = "SELECT b.id, b.author, b.title, b.year, b.price, b.pages, b.isbn, c.cover_type FROM books b JOIN cover_types c ON b.cover_type_id = c.id WHERE b.id = ?";
    private static final String FIND_BY_AUTHOR = "SELECT b.id, b.author, b.title, b.year, b.price, b.pages, b.isbn, c.cover_type FROM books b JOIN cover_types c ON b.cover_type_id = c.id WHERE b.author = ? ORDER BY b.id";
    private static final String FIND_BY_ISBN = "SELECT b.id, b.author, b.title, b.year, b.price, b.pages, b.isbn, c.cover_type FROM books b JOIN cover_types c ON b.cover_type_id = c.id WHERE b.isbn = ?";
    private static final String CREATE_N = "INSERT INTO books (author, title, year, price, pages, isbn, cover_type_id) Values (:author, :title, :year, :price, :pages, :isbn, (SELECT id FROM cover_types WHERE cover_type = :cover))";
    private static final String UPDATE_N = "UPDATE books SET author = :author, title = :title, year = :year, price = :price, pages = :pages, isbn = :isbn, cover_type_id = (SELECT id FROM cover_types WHERE cover_type = :cover) WHERE id = :id";
    private static final String COUNT = "SELECT COUNT(b.id) FROM books b";
    private static final String DELETE = "DELETE FROM books WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private BookDto mapRow(ResultSet resultSet, int num) throws SQLException {
        BookDto bookDto = new BookDto();
        bookDto.setId(resultSet.getLong("id"));
        bookDto.setTitle(resultSet.getString("title"));
        bookDto.setAuthor(resultSet.getString("author"));
        bookDto.setYear(resultSet.getString("year"));
        bookDto.setPrice(resultSet.getBigDecimal("price"));
        bookDto.setIsbn(resultSet.getString("isbn"));
        bookDto.setPages(resultSet.getInt("pages"));
        String coverRaw = resultSet.getString("cover_type");
        bookDto.setCoverDto(BookDto.CoverDto.valueOf(coverRaw));
        return bookDto;
    }

    @Override
    public List<BookDto> findAll() {
        return jdbcTemplate.query(FIND_ALL, this::mapRow);
    }

    @Override
    public List<BookDto> findByAuthor(String author) {
        return jdbcTemplate.query(FIND_BY_AUTHOR, this::mapRow, author);
    }

    @Override
    public BookDto find(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, this::mapRow, id);
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        return jdbcTemplate.queryForObject(FIND_BY_ISBN, this::mapRow, isbn);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = prepareForUpdate(bookDto);
        SqlParameterSource namedParams = new MapSqlParameterSource().addValues(params);
        namedParameterJdbcTemplate.update(CREATE_N, namedParams, keyHolder, new String[]{"id"});
        long id = keyHolder.getKey().longValue();
        return find(id);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Map<String, Object> params = prepareForUpdate(bookDto);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(params);
        namedParameterJdbcTemplate.update(UPDATE_N, namedParameters);
        return bookDto;
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public long countAll() {
        return jdbcTemplate.queryForObject(COUNT, Long.class);
    }

    private static Map<String, Object> prepareForUpdate(BookDto bookDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", bookDto.getId());
        params.put("title", bookDto.getTitle());
        params.put("author", bookDto.getAuthor());
        params.put("year", bookDto.getYear());
        params.put("price", bookDto.getPrice());
        params.put("isbn", bookDto.getIsbn());
        params.put("pages", bookDto.getPages());
        params.put("cover", bookDto.getCoverDto().toString());
        return params;
    }
}
