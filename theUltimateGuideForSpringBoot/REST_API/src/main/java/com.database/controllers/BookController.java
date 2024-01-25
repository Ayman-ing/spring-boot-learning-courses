package com.database.controllers;

import com.database.domain.dto.AuthorDto;
import com.database.domain.dto.BookDto;
import com.database.domain.entities.AuthorEntity;
import com.database.domain.entities.BookEntity;
import com.database.mappers.Mapper;
import com.database.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class BookController {
    private final BookService bookService;
    private final Mapper<BookEntity,BookDto> bookMapper;

    public BookController(BookService bookService, Mapper<BookEntity,BookDto>  bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PutMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto){
        BookEntity bookEntity =bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity= bookService.createBook(isbn,bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity), HttpStatus.CREATED);

    }
    @GetMapping(path="/books")
    public List<BookDto> listBooks(){

        List<BookEntity> books = bookService.findAll();
        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());

    }
    @GetMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn){
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto,HttpStatus.OK);
        }).orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}
