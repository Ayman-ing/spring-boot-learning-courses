package com.database.services;

import com.database.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;


public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();
}
