package ru.itis.blogrestapi.services;

import ru.itis.blogrestapi.dto.AuthorDto;
import ru.itis.blogrestapi.dto.AuthorsPage;

import java.util.Optional;

public interface AuthorsService {
    AuthorsPage getAuthors(int page, Optional<String> sort);

    AuthorDto addAuthor(AuthorDto authorDto);

    AuthorDto updateAuthor(Long id, AuthorDto authorDto);

    AuthorDto deleteAuthor(Long id);
}
