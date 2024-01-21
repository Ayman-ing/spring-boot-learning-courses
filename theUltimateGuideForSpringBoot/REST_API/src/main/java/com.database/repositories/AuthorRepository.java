package com.database.repositories;


import com.database.domain.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {
    Iterable<AuthorEntity> ageLessThan(int age);
}
