package ru.itis.blogrestapi.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ru.itis.blogrestapi.dto.AuthorsPage;
import ru.itis.blogrestapi.models.Author;

@Mapper
public class AuthorsPageMapper {
    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    public AuthorsPage toAuthorsPage(Page<Author> authors) {
        return AuthorsPage.builder()
                .authors(authorMapper.toAuthorDtoList(authors.getContent()))
                .totalPages(authors.getTotalPages())
                .build();
    }
}
