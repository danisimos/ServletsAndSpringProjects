package ru.itis.blogrestapi.dto.mappers;


import org.mapstruct.*;
import ru.itis.blogrestapi.dto.AuthorDto;
import ru.itis.blogrestapi.models.Author;
import ru.itis.blogrestapi.models.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class AuthorMapper {
    public abstract AuthorDto toAuthorDto(Author author);
    public abstract Author toAuthor(AuthorDto authorDto);
    public abstract List<AuthorDto> toAuthorDtoList(List<Author> authors);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateAuthorFromDto(AuthorDto authorDto, @MappingTarget Author author);
}
