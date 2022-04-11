package ru.itis.blogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Автор")
public class AuthorDto {
    public enum State {
        ACTIVE, DELETED
    }

    @Schema(description = "Id", example = "1")
    private Long id;
    @Schema(description = "Имя", example = "Даниил")
    private String firstName;
    @Schema(description = "Фамилия", example = "Коротаев")
    private String lastName;
    @Schema(description = "Создан", example = "2022-04-06T18:23:53.697+00:00")
    private Timestamp createdAt;
    @Schema(description = "Обновлен", example = "2022-04-06T18:23:53.697+00:00")
    private Timestamp updatedAt;
    @Schema(description = "Статус", example = "CONFIRMED")
    private State state;
}
