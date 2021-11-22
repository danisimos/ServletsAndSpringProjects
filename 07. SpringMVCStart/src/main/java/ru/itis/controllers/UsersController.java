package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersController implements Controller {
    UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService)  {
        this.usersService = usersService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UserDto> userDtoList = usersService.getUsers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userDtoList);
        modelAndView.setViewName("users");

        return modelAndView;
    }
}
