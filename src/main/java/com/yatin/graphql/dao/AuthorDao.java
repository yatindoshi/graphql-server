package com.yatin.graphql.dao;

import com.yatin.graphql.domain.Author;

import java.util.List;

public class AuthorDao {
    private final List<Author> authors;

    public AuthorDao(List<Author> authors) {
        this.authors = authors;
    }

    public Author getAuthor(String id) {
        return authors.stream()
                .filter(author -> id.equals(author.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public List<Author> getAuthors(){
        return authors;
    }
}
