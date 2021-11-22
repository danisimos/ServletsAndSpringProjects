package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.dto.UserForm;
import ru.itis.services.UsersService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUsersPage(Model model) {
        model.addAttribute("users", usersService.getUsers());
        return "users";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(UserForm userForm) {
        usersService.saveUser(userForm);
        return "redirect:users";
    }

}
