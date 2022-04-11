package ru.itis.shop.controllers;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.shop.dto.SignUpDto;
import ru.itis.shop.services.SignUpService;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping
    public String signUp(SignUpDto accountForm) throws TemplateException, IOException {
        signUpService.signUp(accountForm);
        return "redirect:/signUp";
    }
}
