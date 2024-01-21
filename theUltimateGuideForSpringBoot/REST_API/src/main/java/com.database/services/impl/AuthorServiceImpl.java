package com.database.services.impl;

import com.database.domain.entities.AuthorEntity;
import com.database.repositories.AuthorRepository;
import com.database.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
            return authorRepository.save(authorEntity);
            }
}
