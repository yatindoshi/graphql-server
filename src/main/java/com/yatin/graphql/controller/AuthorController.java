package com.yatin.graphql.controller;

import com.yatin.graphql.dao.AuthorDao;
import com.yatin.graphql.dao.PostDao;
import com.yatin.graphql.domain.Author;
import com.yatin.graphql.domain.Post;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final PostDao postDao;

    private final AuthorDao authorDao;

    public AuthorController(PostDao postDao, AuthorDao authorDao) {
        this.postDao = postDao;
        this.authorDao = authorDao;
    }

    @QueryMapping
    public List<Author> authors() {
        return authorDao.getAuthors();
    }

    @SchemaMapping
    public List<Post> posts(Author author) {
        return postDao.getAuthorPosts(author.getId());
    }
}
