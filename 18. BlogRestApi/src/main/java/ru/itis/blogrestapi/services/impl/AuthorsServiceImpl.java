package ru.itis.blogrestapi.services.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.blogrestapi.dto.AuthorDto;
import ru.itis.blogrestapi.dto.AuthorsPage;
import ru.itis.blogrestapi.dto.mappers.AuthorMapper;
import ru.itis.blogrestapi.dto.mappers.AuthorsPageMapper;
import ru.itis.blogrestapi.exceptions.AuthorNotFoundException;
import ru.itis.blogrestapi.models.Author;
import ru.itis.blogrestapi.repositories.AuthorsRepository;
import ru.itis.blogrestapi.services.AuthorsService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorsRepository authorsRepository;
    private final AuthorMapper authorMapper;
    private final AuthorsPageMapper authorsPageMapper;

    @Value("${blog.default-page-size}")
    private int defaultPageSize;

    @Override
    public AuthorsPage getAuthors(int page, Optional<String> sort) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by(sort.orElse("id")).ascending());
        return authorsPageMapper.toAuthorsPage(authorsRepository.findAll(pageRequest));
    }

    @Override
    public AuthorDto addAuthor(AuthorDto authorDto) {
        AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);
        authorDto.setState(AuthorDto.State.ACTIVE);

        return authorMapper.toAuthorDto(authorsRepository.save(authorMapper.toAuthor(authorDto)));
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto newAuthor) {
        Author author = authorsRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
        authorMapper.updateAuthorFromDto(newAuthor, author);

        return authorMapper.toAuthorDto(authorsRepository.save(author));
    }

    @Override
    public AuthorDto deleteAuthor(Long id) {
        Author author = authorsRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
        author.setState(Author.State.DELETED);

        return authorMapper.toAuthorDto(authorsRepository.save(author));
    }
}
