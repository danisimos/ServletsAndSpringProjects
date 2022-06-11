package ru.itis.blogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Список постов на странице и общее количество таких страниц с постами")
public class PostsPage {
    @Schema(description = "Список постов")
    private List<PostDto> posts;
    @Schema(description = "Количество страниц", example = "3")
    private Integer totalPages;
}
