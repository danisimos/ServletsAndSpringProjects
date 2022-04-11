package ru.itis.blogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Пост")
public class PostDto {
    public enum State {
        DRAFT, DELETED, PUBLISHED
    }
    @Schema(description = "Id", example = "1")
    private Long id;
    @Schema(description = "Создан", example = "2022-04-06T18:23:53.697+00:00")
    private Timestamp createdAt;
    @Schema(description = "Обновлен", example = "2022-04-06T18:23:53.697+00:00")
    private Timestamp updatedAt;
    @Schema(description = "Название поста", example = "Как научиться документировать код")
    private String title;
    @Schema(description = "Текст поста", example = "Очень просто")
    private String text;
    @Schema(description = "Статус", example = "DRAFT")
    private State state;
}
