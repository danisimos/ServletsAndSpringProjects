package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.model.User;

import java.util.List;

public interface UsersService {
    public List<UserDto> getUsers();
    public User saveUser(UserForm userForm);
}
