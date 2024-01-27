package com.database.controllers;

import com.database.TestDataUtil;
import com.database.domain.dto.AuthorDto;
import com.database.domain.entities.AuthorEntity;
import com.database.services.AuthorService;
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
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
    private AuthorService authorService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    public AuthorControllerIntegrationTests(AuthorService authorService, MockMvc mockMvc ) {

        this.authorService = authorService;
        this.mockMvc = mockMvc;
        this.objectMapper= new  ObjectMapper();
    }

    @Test
    public void testThatCreatAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)).andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void testThatCreatAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("80"));
    }
    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception{
        AuthorEntity testAuthorEntityA =TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntityA);
        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Abigail Rose"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value("80"));
    }
    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception{
        AuthorEntity testAuthorEntityA =TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntityA);
        AuthorEntity testAuthorEntityB =TestDataUtil.createTestAuthorB();
        authorService.save(testAuthorEntityB);
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/" + testAuthorEntityB.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatGetAuthorReturnsHttpStatus404WhenAuthorDoesNotExist() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/99")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExist() throws Exception{
        AuthorEntity testAuthorEntityA =TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/" + testAuthorEntityA.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("80"));
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenAuthorDoesNotExist() throws Exception{
        AuthorDto testAuthorDtoA =TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);
        AuthorDto testAuthorDtoA =TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatFullUpdateAuthorUpdatesExistingAuthor() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);



        AuthorEntity authorDto = TestDataUtil.createTestAuthorB();
        authorDto.setId(savedAuthor.getId());
        String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoUpdateJson)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge()));
    }
    @Test
    public void testThatPartialUpdateAuthorReturnsHttpStatus200OkWhenAuthorExists() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);
        AuthorDto testAuthorDtoA =TestDataUtil.createTestAuthorDtoA();
        testAuthorDtoA.setName("UPDATED");
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);
        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatPartialUpdateAuthorReturnsUpdatedAuthor() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);
        AuthorDto testAuthorDtoA =TestDataUtil.createTestAuthorDtoA();
        testAuthorDtoA.setName("UPDATED");
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);
        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("UPDATED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDtoA.getAge()));
    }
    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForNoneExisitingAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/999")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForExisitingAuthor() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);

        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
