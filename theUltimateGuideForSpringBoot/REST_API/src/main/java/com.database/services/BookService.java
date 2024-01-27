package com.database.services;

import com.database.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);
}
