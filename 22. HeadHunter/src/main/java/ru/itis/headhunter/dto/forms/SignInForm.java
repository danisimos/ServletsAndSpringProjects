package ru.itis.headhunter.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInForm {
    @Email
    @Schema(description = "your email", example = "daniil.korotaev@gmail.com",
            nullable = false, required = true, minLength = 2, maxLength = 50)
    private String email;
    @NotNull
    @Schema(description = "your password", example = "qwerty",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String password;
}
