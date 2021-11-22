package ru.itis.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.dto.UserForm;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostUserController implements Controller {
    UsersService usersService;

    public PostUserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserForm userForm = UserForm.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .age(Integer.parseInt(request.getParameter("age")))
                .build();

        usersService.saveUser(userForm);

        return null;
    }
}
