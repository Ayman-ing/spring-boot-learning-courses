package com.database.dao.impl;

import com.database.dao.BookDao;
import com.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;
    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO BOOKS (isbn,title,author_id) VALUES (?,?,?)",
                book.getIsbn(),book.getTitle(),book.getAuthorId());

    }
}

