package ru.itis.blogrestapi.validation.annotations;

import ru.itis.blogrestapi.validation.validators.NotContainsWordsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotContainsWordsValidator.class)
public @interface NotContainsWords {
    String message() default "contains forbidden words";

    String[] words() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
