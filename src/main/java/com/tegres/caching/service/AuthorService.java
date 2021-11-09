package com.tegres.caching.service;

import com.tegres.caching.domain.Author;

import java.util.Collection;
import java.util.Optional;

public interface AuthorService {
    Collection<Author> findAllAuthors();
    Optional<Author> findAuthorByName(String name);
}
