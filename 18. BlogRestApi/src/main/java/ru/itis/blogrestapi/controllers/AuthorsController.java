package ru.itis.blogrestapi.controllers;

import lombok.RequiredArgsConstructor;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.blogrestapi.dto.AuthorDto;
import ru.itis.blogrestapi.dto.AuthorsPage;
import ru.itis.blogrestapi.dto.PostDto;
import ru.itis.blogrestapi.dto.PostsPage;
import ru.itis.blogrestapi.dto.mappers.AuthorMapper;
import ru.itis.blogrestapi.dto.mappers.PostMapper;
import ru.itis.blogrestapi.models.Author;
import ru.itis.blogrestapi.models.Post;
import ru.itis.blogrestapi.repositories.AuthorsRepository;
import ru.itis.blogrestapi.services.AuthorsService;
import ru.itis.blogrestapi.services.PostsService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorsController {
    private final AuthorsService authorsService;
    private final PostsService postsService;
    private final AuthorMapper authorMapper;
    private final PostMapper postMapper;
    private final AuthorsRepository authorsRepository;

    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(@RequestParam int page,
                                                  @RequestParam(required = false) Optional<String> sort) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(authorsService.getAuthors(page, sort));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto authorDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(authorDto));
    }

    @PutMapping("/{author_id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable(name = "author_id") Long id,
                                                  @RequestBody AuthorDto authorDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.updateAuthor(id, authorDto));
    }

    @DeleteMapping("/{author_id}")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable(name = "author_id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.deleteAuthor(id));
    }

    @GetMapping("/{author_id}/posts")
    public ResponseEntity<PostsPage> getPostsByAuthor(@PathVariable(name = "author_id") Long id,
                                                      @RequestParam int page,
                                                      @RequestParam(required = false) Optional<String> sort) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(postsService.getPostsByAuthor(id, page, sort));
    }

    @PostMapping("/{author_id}/posts")
    public ResponseEntity<PostDto> addPostToAuthor(@PathVariable(name = "author_id") Long id,
                                                   @RequestBody PostDto postDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.addPostToAuthor(id, postDto));
    }

    @PutMapping("/{author_id}/posts/{post_id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "author_id") Long authorId,
                                              @PathVariable(name = "post_id") Long postId,
                                              @RequestBody PostDto postDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postsService.updatePost(postId, postDto));
    }

    @DeleteMapping("/{author_id}/posts/{post_id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable(name = "author_id") Long authorId,
                                              @PathVariable(name = "post_id") Long postId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postsService.deletePost(postId));
    }
}
