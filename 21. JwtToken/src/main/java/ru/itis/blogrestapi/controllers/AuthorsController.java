package ru.itis.blogrestapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.mapstruct.Context;
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

import javax.validation.Valid;
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

    @Operation(summary = "Получение всех авторов с пагинацией и сортировкой")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с авторами", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorsPage.class))
            })
    })
    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(@RequestParam int page,
                                                  @RequestParam(required = false) Optional<String> sort) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorsService.getAuthors(page, sort));
    }

    @Operation(summary = "Добавление нового автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Созданный автор", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))
            })
    })
    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(authorDto));
    }

    @Operation(summary = "Обновление автора по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновленный автор", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))
            })
    })
    @PutMapping("/{author_id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable(name = "author_id") Long id,
                                                  @Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.updateAuthor(id, authorDto));
    }

    @Operation(summary = "Удаление автора по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаленный автор со статусом DELETED", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))
            })
    })
    @DeleteMapping("/{author_id}")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable(name = "author_id") Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.deleteAuthor(id));
    }

    @Operation(summary = "Получение всех постов автора с пагинацией и сортировкой")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с постами", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostsPage.class))
            })
    })
    @GetMapping("/{author_id}/posts")
    public ResponseEntity<PostsPage> getPostsByAuthor(@PathVariable(name = "author_id") Long id,
                                                      @RequestParam int page,
                                                      @RequestParam(required = false) Optional<String> sort) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postsService.getPostsByAuthor(id, page, sort));
    }

    @Operation(summary = "Добавление поста автору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Созданный пост", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))
            })
    })
    @PostMapping("/{author_id}/posts")
    public ResponseEntity<PostDto> addPostToAuthor(@PathVariable(name = "author_id") Long id,
                                                   @Valid @RequestBody PostDto postDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.addPostToAuthor(id, postDto));
    }

    @Operation(summary = "Обновление поста по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновленный пост", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))
            })
    })
    @PutMapping("/{author_id}/posts/{post_id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "author_id") Long authorId,
                                              @PathVariable(name = "post_id") Long postId,
                                              @Valid @RequestBody PostDto postDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postsService.updatePost(postId, postDto));
    }

    @Operation(summary = "Удаление поста по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаленный пост со статусом DELETED", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))
            })
    })
    @DeleteMapping("/{author_id}/posts/{post_id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable(name = "author_id") Long authorId,
                                              @PathVariable(name = "post_id") Long postId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postsService.deletePost(postId));
    }

    @Operation(summary = "Получение всех постов во вкладке избранное автора с пагинацией и сортировкой")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с постами", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostsPage.class))
            })
    })
    @GetMapping("/{author_id}/posts/favorites")
    public ResponseEntity<PostsPage> getFavoritesPosts(@PathVariable(name = "author_id") Long authorId,
                                                       @RequestParam int page,
                                                       @RequestParam(required = false) Optional<String> sort) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postsService.getPostsByAuthorInFavorites(authorId, page, sort));
    }

    @Operation(summary = "Добавление поста в избранное автору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Добавленный пост", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))
            })
    })
    @PostMapping("/{author_id}/posts/addToFavorite")
    public ResponseEntity<PostDto> addPostToFavoriteToAuthor(@PathVariable(name = "author_id") Long authorId,
                                                             @RequestParam Long postId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postsService.addPostToFavoriteToAuthor(authorId, postId));
    }

    @Operation(summary = "Удаление поста из вкладки избранное автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаленный из избранного пост", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))
            })
    })
    @DeleteMapping("/{author_id}/posts/deleteFromFavorite")
    public ResponseEntity<PostDto> deletePostFromFavoriteFromAuthor(@PathVariable(name = "author_id") Long authorId,
                                                             @RequestParam Long postId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postsService.deletePostFromFavoriteFromAuthor(authorId, postId));
    }
}
