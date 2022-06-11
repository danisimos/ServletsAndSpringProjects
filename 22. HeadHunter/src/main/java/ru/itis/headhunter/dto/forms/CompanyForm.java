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
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyForm {
    @NotBlank(message = "company name cannot be empty")
    @NotContainsWords(words = {"bad", "awful"})
    @Schema(description = "company name", example = "Skuratov",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String name;
    @NotBlank(message = "company description cannot be empty")
    @NotContainsWords(words = {"bad", "words"})
    @Schema(description = "company description", example = "Skuratov Coffee",
            nullable = false, required = true, minLength = 2, maxLength = 50)
    private String description;
}
