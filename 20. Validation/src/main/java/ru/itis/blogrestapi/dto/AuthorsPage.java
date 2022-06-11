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
@Schema(description = "Список авторов на странице и общее количество таких страниц с авторами")
public class AuthorsPage {
    @Schema(description = "Список авторов")
    private List<AuthorDto> authors;
    @Schema(description = "Количество страниц", example = "3")
    private Integer totalPages;
}
