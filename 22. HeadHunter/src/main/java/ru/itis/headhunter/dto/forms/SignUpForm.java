package ru.itis.headhunter.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.headhunter.validation.annotations.NotContainsWords;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    @NotBlank(message = "account first name cannot be empty")
    @NotContainsWords(words = {"firstName", "awful"})
    @Schema(description = "your firstname", example = "Daniil",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String firstName;
    @NotBlank(message = "account last name cannot be empty")
    @Schema(description = "your lastname", example = "Korotaev",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String lastName;
    @Email(message = "account email is invalid")
    @Schema(description = "your email", example = "daniil.korotaev@gmail.com",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String email;
    @NotBlank(message = "account password cannot be empty")
    @NotContainsWords(words = {"password", "qwerty"})
    @Schema(description = "your password", example = "qwerty",
            nullable = false, required = true, minLength = 2, maxLength = 20)
    private String password;
}
