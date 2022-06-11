package ru.itis.blogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blogrestapi.validation.annotations.NotSameNames;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Автор")
@NotSameNames(names = {"firstName", "lastName"}, message = "{names} are same")
public class AuthorDto {
    public enum State {
        ACTIVE, DELETED
    }

    @Schema(description = "Id", example = "1")
    private Long id;

    @NotBlank(message = "first name cannot be empty")
    @Size(min = 2, max = 20, message = "first name min size must be {min}, max {max}")
    @Schema(description = "Имя", example = "Даниил",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String firstName;

    @NotBlank(message = "last name cannot be empty")
    @Size(min = 2, max = 20, message = "last name min size must be {min}, max {max}")
    @Schema(description = "Фамилия", example = "Коротаев",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String lastName;

    @Schema(description = "Создан", example = "2022-04-06T18:23:53.697+00:00")
    private Timestamp createdAt;

    @Schema(description = "Обновлен", example = "2022-04-06T18:23:53.697+00:00")
    private Timestamp updatedAt;

    @Schema(description = "Статус", example = "CONFIRMED")
    private State state;
}
