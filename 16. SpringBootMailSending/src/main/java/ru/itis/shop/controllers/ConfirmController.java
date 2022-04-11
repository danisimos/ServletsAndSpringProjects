package ru.itis.shop.controllers;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.shop.services.ConfirmService;
import ru.itis.shop.services.SendEmailService;

import java.io.IOException;

@Controller
@RequestMapping("/confirm")
@RequiredArgsConstructor
public class ConfirmController {
    private final ConfirmService confirmService;
    private final SendEmailService sendEmailService;

    @GetMapping("/{code}")
    public String confirm(Model model, @PathVariable("code") String code) {
        if(confirmService.confirm(code)) {
            model.addAttribute("message", "Successful confirmed");
            return "confirmed";
        }

        model.addAttribute("confirmCode", code);
        return "failed_confirm";
    }

    @GetMapping("/sendConfirmEmail/{code}")
    public String sendConfirmEmail(@PathVariable("code") String code) throws TemplateException, IOException {
        sendEmailService.send(code);

        return "redirect:/signUp";
    }
}
