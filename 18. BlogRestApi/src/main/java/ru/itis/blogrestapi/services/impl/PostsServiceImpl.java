package ru.itis.blogrestapi.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.blogrestapi.dto.PostDto;
import ru.itis.blogrestapi.dto.PostsPage;
import ru.itis.blogrestapi.dto.mappers.PostMapper;
import ru.itis.blogrestapi.dto.mappers.PostsPageMapper;
import ru.itis.blogrestapi.exceptions.AuthorNotFoundException;
import ru.itis.blogrestapi.exceptions.PostNotFoundException;
import ru.itis.blogrestapi.models.Author;
import ru.itis.blogrestapi.models.Post;
import ru.itis.blogrestapi.repositories.AuthorsRepository;
import ru.itis.blogrestapi.repositories.PostsRepository;
import ru.itis.blogrestapi.services.PostsService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;
    private final AuthorsRepository authorsRepository;
    private final PostsPageMapper postsPageMapper;
    private final PostMapper postMapper;

    @Value("${blog.default-page-size}")
    private int defaultPageSize;

    @Override
    public PostsPage getPostsByAuthor(Long id, int page, Optional<String> sort) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by(sort.orElse("id")).ascending());
        return postsPageMapper.toPostsPage(postsRepository.findAllByAuthorId(id, pageRequest));
    }

    @Override
    public PostDto addPostToAuthor(Long id, PostDto postDto) {
        Post post = postMapper.toPost(postDto);
        post.setState(Post.State.PUBLISHED);
        Author author = authorsRepository.findById(id).orElseThrow(AuthorNotFoundException::new);

        post.setAuthor(author);
        author.getPosts().add(post);

        authorsRepository.save(author);

        return postMapper.toPostDto(postsRepository.save(post));
    }

    @Override
    public PostDto updatePost(Long postId, PostDto postDto) {
        Post post = postsRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        postMapper.updatePostFromDto(postDto, post);

        return postMapper.toPostDto(postsRepository.save(post));
    }

    @Override
    public PostDto deletePost(Long postId) {
        Post post = postsRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        post.setState(Post.State.DELETED);

        return postMapper.toPostDto(postsRepository.save(post));
    }
}
