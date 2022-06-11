package ru.itis.blogrestapi.validation.validators;

import ru.itis.blogrestapi.validation.annotations.NotContainsWords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.function.Function;

public class NotContainsWordsValidator implements ConstraintValidator<NotContainsWords, String> {
    private String[] words;

    @Override
    public void initialize(NotContainsWords constraint) {
        this.words = constraint.words();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(words).noneMatch(value::contains);
    }
}
