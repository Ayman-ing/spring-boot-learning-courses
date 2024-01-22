package com.database.services.impl;

import com.database.domain.entities.BookEntity;
import com.database.repositories.BookRepository;
import com.database.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }
    public BookEntity  createBook(String isbn,BookEntity bookEntity){
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(),false)
                    .collect(Collectors.toList());
    }
}
