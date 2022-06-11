package ru.itis.blogrestapi.dto.mappers;

import org.mapstruct.*;
import ru.itis.blogrestapi.dto.PostDto;
import ru.itis.blogrestapi.models.Post;

import java.util.List;

@Mapper
public abstract class PostMapper {
    public abstract Post toPost(PostDto postDto);
    public abstract PostDto toPostDto(Post post);
    public abstract List<PostDto> toPostDtoList(List<Post> posts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updatePostFromDto(PostDto postDto, @MappingTarget Post post);
}
