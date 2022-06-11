package ru.itis.blogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blogrestapi.validation.annotations.NotContainsWords;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "title cannot be empty")
    @Size(min = 3, max = 20, message = "title min size must be {min}, max {max}")
    @Schema(description = "Название поста", example = "Как научиться документировать код",
            nullable = false, minLength = 3, maxLength = 20, required = true)
    private String title;

    @NotBlank(message = "text cannot be empty")
    @Size(min = 3, max = 200, message = "text min size must be {min}, max {max}")
    @NotContainsWords(words = {"firstForbiddenWord", "secondForbiddenWord"}, message = "text cannot contains forbidden words: {words}")
    @Schema(description = "Текст поста", example = "Очень просто",
            nullable = false, minLength = 3, maxLength = 200, required = true)
    private String text;

    @Schema(description = "Статус", example = "DRAFT")
    private State state;
}
