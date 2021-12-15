package com.tegres.caching.resource;

import com.tegres.caching.constants.ComponentConstants;
import com.tegres.caching.domain.Author;
import com.tegres.caching.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorResource {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Qualifier(ComponentConstants.SERVICE_AUTHOR)
    private AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/authors")
    public ResponseEntity<Collection<Author>> getAllAuthorData() {
        LOG.info("About to retrieve Authors Data");
        final Collection<Author> authors = authorService.findAllAuthors();
        LOG.info("Authors Data retrieved");
        return ResponseEntity.ok().body(authors);
    }

    @GetMapping(value = "/author/{name}")
    public ResponseEntity<Author> getAuthor(@PathVariable(value = "name") String authorName) {
        Assert.notNull(authorName, () -> "Author name not specified.");
        LOG.info("About to retrieve Author Data");
        final Author author = authorService.findAuthorByName(authorName).get();
        LOG.info("Author Data retrieved");
        return ResponseEntity.ok().body(author);
    }
}
