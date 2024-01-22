package com.database.services;

import com.database.domain.entities.BookEntity;

import java.util.List;

public interface BookService {
    public BookEntity createBook(String isbn,BookEntity bookEntity);

    List<BookEntity> findAll();
}
