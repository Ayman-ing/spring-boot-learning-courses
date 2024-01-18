package com.database.dao;


import com.database.dao.impl.BookDaoImpl;
import com.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class BookDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookDaoImpl undertest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = Book.builder().isbn("978-1-2345-6789-0").title("the Shadow in the Attic")
                        .authorId(1L).build();
        undertest.create(book);


        verify(jdbcTemplate).update(eq("INSERT INTO BOOKS (isbn,title,author_id) VALUES (?,?,?)"),
                eq("978-1-2345-6789-0"),
                eq("the Shadow in the Attic"),
                eq(1L));
    }
}
