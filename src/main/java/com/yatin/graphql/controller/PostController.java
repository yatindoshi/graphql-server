package com.yatin.graphql.controller;

import com.yatin.graphql.dao.AuthorDao;
import com.yatin.graphql.dao.PostDao;
import com.yatin.graphql.domain.Author;
import com.yatin.graphql.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class PostController {

    private final PostDao postDao;
    private final AuthorDao authorDao;

    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {
        return postDao.getRecentPosts(count, offset);
    }

    @QueryMapping
    public List<Post> postsFromAuthor( @Argument String authorId) {
        return postDao.getAuthorPosts(authorId);
    }

    @SchemaMapping
    public Author author(Post post) {
        return authorDao.getAuthor(post.getAuthorId());
    }

    @SchemaMapping(typeName="Post", field="first_author")
    public Author getFirstAuthor(Post post) {
        return authorDao.getAuthor(post.getAuthorId());
    }

    @MutationMapping
    public Post createPost(@Argument String title, @Argument String text,
                           @Argument String category, @Argument String authorId) {
        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle(title);
        post.setText(text);
        post.setCategory(category);
        post.setAuthorId(authorId);
        postDao.savePost(post);

        return post;
    }

}