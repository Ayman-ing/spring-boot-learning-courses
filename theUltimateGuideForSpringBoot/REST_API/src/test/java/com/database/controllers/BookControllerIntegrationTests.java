package com.database.controllers;

import com.database.TestDataUtil;
import com.database.domain.dto.BookDto;
import com.database.domain.entities.BookEntity;
import com.database.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class BookControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private BookService bookService;
    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc,BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper=new ObjectMapper();
        this.bookService = bookService;
    }

    @Test
    public void testThatCreatBookSuccessfullyReturnsHttp201Created() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(createBookJson)).andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void testThatCreatBookSuccessfullyReturnsCreatedBook() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);

        String authorJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+ bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(bookDto.getAuthor()));
    }

    @Test
    public void testThatListBooksSuccessfullyReturnsHttp200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books" )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatListBooksReturnsBook() throws Exception {
        BookEntity testBookA = TestDataUtil.createTestBookA(null);
        bookService.createBook(testBookA.getIsbn(),testBookA);
        mockMvc.perform(MockMvcRequestBuilders.get("/books" )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value("978-1-2345-6789-0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Shadow in the Attic"));

    }

}
