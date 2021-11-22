package ru.itis.services.impl;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(UserDto::from).toList();
    }

    @Override
    public User saveUser(UserForm userForm) {
        return userRepository.save(UserForm.to(userForm));
    }
}
