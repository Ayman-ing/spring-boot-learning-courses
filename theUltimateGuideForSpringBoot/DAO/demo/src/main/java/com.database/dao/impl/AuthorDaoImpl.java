package com.database.dao.impl;

import com.database.dao.AuthorDao;
import com.database.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;
    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO authors(id,name,age) values(?,?,?)"
        ,author.getId(),author.getName(),author.getAge());

    }
}
