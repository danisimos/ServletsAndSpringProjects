package ru.itis.blogrestapi.services;

import ru.itis.blogrestapi.dto.PostDto;
import ru.itis.blogrestapi.dto.PostsPage;

import java.util.Optional;

public interface PostsService {
    PostsPage getPostsByAuthor(Long id, int page, Optional<String> sort);

    PostDto addPostToAuthor(Long id, PostDto postDto);

    PostDto updatePost(Long postId, PostDto postDto);

    PostDto deletePost(Long postId);
}
