package com.database.controllers;

import com.database.domain.dto.AuthorDto;
import com.database.domain.entities.AuthorEntity;
import com.database.mappers.Mapper;
import com.database.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    private final AuthorService authorService;
    private final Mapper<AuthorEntity,AuthorDto> authorMapper;

    public AuthorController(final AuthorService authorService,Mapper<AuthorEntity,AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path="/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author){
        AuthorEntity authorEntity =  authorMapper.mapFrom(author);
        AuthorEntity saveAuthorEntity =authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(saveAuthorEntity), HttpStatus.CREATED) ;
    }
}
