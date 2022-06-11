package ru.itis.blogrestapi.validation.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationExceptionDto {
    private String message;
    private String object;
}
