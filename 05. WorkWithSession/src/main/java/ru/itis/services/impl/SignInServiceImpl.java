package ru.itis.services.impl;

import ru.itis.dto.SignInForm;
import ru.itis.filters.CookieFilter;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.SignInService;

import javax.servlet.http.Cookie;
import java.util.Optional;

public class SignInServiceImpl implements SignInService {
    UserRepository userRepository;
    User user;

    public SignInServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signIn(SignInForm signInForm) {
        Optional<User> user = userRepository.findByLogin(signInForm.getLogin());

        if(user.isEmpty()) return false;
        this.user = user.get();

        return user.get().getPassword().equals(signInForm.getPassword());
    }

    @Override
    public User getUser() {
        return this.user;
    }
}
