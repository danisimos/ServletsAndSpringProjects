package ru.itis.services;

import freemarker.template.TemplateException;
import ru.itis.dto.SignUpDto;

import java.io.IOException;

public interface SignUpService {
    void signUp(SignUpDto accountForm) throws IOException, TemplateException;
}
