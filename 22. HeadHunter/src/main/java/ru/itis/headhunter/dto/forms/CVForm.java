package ru.itis.headhunter.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.validation.annotations.NotContainsWords;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CVForm {
    @NotBlank(message = "CV title cannot be empty")
    @NotContainsWords(words = {"bad", "awful"})
    @Schema(description = "cv title", example = "java developer",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String title;
    @NotBlank(message = "CV text cannot be empty")
    @NotContainsWords(words = {"bad", "awful"})
    @Schema(description = "cv text", example = "i am java developer...",
            nullable = false, required = true, minLength = 2, maxLength = 500)
    private String text;
}
