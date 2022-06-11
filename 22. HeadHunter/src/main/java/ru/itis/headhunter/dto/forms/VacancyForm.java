package ru.itis.headhunter.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.dto.VacancyDto;
import ru.itis.headhunter.models.Company;
import ru.itis.headhunter.models.VacancyResponse;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyForm {
    public enum Specialization {
        IT, SALES, MANAGEMENT, SCIENCE, MEDICINE
    }

    @NotBlank(message = "vacancy title cannot be empty")
    @Schema(description = "vacancy title", example = "Java developer",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String title;
    @NotBlank(message = "vacancy description cannot be empty")
    @Schema(description = "vacancy description", example = "junior java developer",
            nullable = false, required = true, minLength = 2, maxLength = 100)
    private String description;
    @NotNull
    @Min(value = 1)
    @Schema(description = "vacancy salary", example = "15000",
            nullable = false, required = true, minimum = "1", maximum = "1000000")
    private Long salary;
    @NotNull
    @Schema(description = "vacancy specialization(IT, MEDICINE, SALES)", example = "MEDICINE",
            nullable = false, required = true, minLength = 2, maxLength = 40)
    private Specialization specialization;
}
