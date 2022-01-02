package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.dto.ProfileForm;
import ru.itis.services.ProfilesService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class ProfilesController {
    private final ProfilesService profilesService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUsersPage(Model model) {
        model.addAttribute("users", profilesService.getUsers());
        System.out.println(profilesService.getUsers());
        return "users";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(ProfileForm profileForm) {
        profilesService.saveUser(profileForm);
        return "redirect:users";
    }

}
