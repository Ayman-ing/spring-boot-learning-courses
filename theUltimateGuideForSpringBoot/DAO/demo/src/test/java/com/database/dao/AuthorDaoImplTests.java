package com.database.dao;

import com.database.dao.impl.AuthorDaoImpl;
import com.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDaoImpl undertest;
    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = Author.builder().id(1L).name("Ayman feki").age(80).build();
        undertest.create(author);
        verify(jdbcTemplate).update(eq("INSERT INTO authors(id,name,age) values(?,?,?)"
        ), eq(1L), eq("Ayman feki"), eq(80));
    }

}
