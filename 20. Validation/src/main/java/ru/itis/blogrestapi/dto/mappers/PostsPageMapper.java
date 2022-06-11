package ru.itis.blogrestapi.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ru.itis.blogrestapi.dto.PostsPage;
import ru.itis.blogrestapi.models.Post;

@Mapper
public class PostsPageMapper {
    private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    public PostsPage toPostsPage(Page<Post> posts) {
        return PostsPage.builder()
                .posts(postMapper.toPostDtoList(posts.getContent()))
                .totalPages(posts.getTotalPages())
                .build();
    }
}
