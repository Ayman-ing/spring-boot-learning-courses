package com.database.services;

import com.database.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;


public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);
}
