package com.database.services;

import com.database.domain.entities.BookEntity;

public interface BookService {
    public BookEntity createBook(String isbn,BookEntity bookEntity);

}
