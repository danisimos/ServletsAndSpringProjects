package ru.itis.shop.services;

import freemarker.template.TemplateException;
import ru.itis.shop.dto.SignUpDto;

import java.io.IOException;

public interface SignUpService {
    void signUp(SignUpDto accountForm) throws IOException, TemplateException;
}
