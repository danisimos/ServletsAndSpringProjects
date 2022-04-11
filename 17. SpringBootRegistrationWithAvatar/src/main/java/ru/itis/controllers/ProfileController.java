package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.dto.AccountDto;
import ru.itis.services.ProfileService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{login}")
    public String getProfile(Model model, @PathVariable(name = "login") String login) {
        Optional<AccountDto> accountDto = profileService.getAccountByLogin(login);
        if(accountDto.isPresent()) {
            model.addAttribute("account", accountDto.get());
            return "profile";
        }

        return "profile_not_found";
    }
}
