package ru.itis.headhunter.exceptions.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationExceptionResponse {
    private List<ValidationExceptionDto> exceptions;
}
