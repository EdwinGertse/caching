package com.tegres.caching.service;

import com.tegres.caching.constants.Caches;
import com.tegres.caching.constants.ComponentConstants;
import com.tegres.caching.domain.Author;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service(ComponentConstants.SERVICE_AUTHOR)
public class AuthorServiceImpl implements AuthorService {

    @Cacheable(cacheNames = Caches.AUTHORS_CACHE,
            keyGenerator = ComponentConstants.APPLICATION_CACHE_KEY_GENERATOR)
    @Override
    public Collection<Author> findAllAuthors() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableCollection(constructAuthors());
    }

    @Cacheable(cacheNames = Caches.AUTHOR_CACHE,
            keyGenerator = ComponentConstants.APPLICATION_CACHE_KEY_GENERATOR)
    @Override
    public Optional<Author> findAuthorByName(String name) {
        Assert.notNull(name, () -> "Author name should not be null");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Author result = constructAuthors()
                .stream()
                .filter(author -> author.getName().equalsIgnoreCase(name)).findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Author with name %s not found.", name)));
        return Optional.ofNullable(result);
    }

    private Collection<Author> constructAuthors() {
        return Arrays.asList(new Author(1L, "John"),
                new Author(2L, "Jane"));
    }
}
