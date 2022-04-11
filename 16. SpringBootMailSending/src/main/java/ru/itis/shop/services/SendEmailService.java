package ru.itis.shop.services;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface SendEmailService {
    void send(String confirmCode) throws TemplateException, IOException;
}
