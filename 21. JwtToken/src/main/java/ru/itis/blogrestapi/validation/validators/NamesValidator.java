package ru.itis.blogrestapi.validation.validators;

import org.springframework.beans.BeanWrapperImpl;
import ru.itis.blogrestapi.validation.annotations.NotSameNames;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class NamesValidator implements ConstraintValidator<NotSameNames, Object> {
    private String[] fields;

    @Override
    public void initialize(NotSameNames constraintAnnotation) {
        this.fields = constraintAnnotation.names();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        List<String> fieldValues = new ArrayList<>();

        for(String fieldName: fields) {
            BeanWrapperImpl wrapper = new BeanWrapperImpl(object);
            fieldValues.add((String) wrapper.getPropertyValue(fieldName));
        }

        return fieldValues.size() == fieldValues.stream().distinct().count();
    }
}
