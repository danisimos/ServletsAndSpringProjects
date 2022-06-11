package ru.itis.headhunter.exceptions.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationExceptionDto {
    private String message;
    private String object;
}
