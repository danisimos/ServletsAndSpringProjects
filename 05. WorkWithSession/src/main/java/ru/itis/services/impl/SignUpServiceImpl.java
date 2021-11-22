package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.dto.SignUpForm;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.SignUpService;

public class SignUpServiceImpl implements SignUpService {
    UserRepository userRepository;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(SignUpForm signUpForm) {
        User user = User.builder()
                .login(signUpForm.getLogin())
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .age(signUpForm.getAge())
                .password(signUpForm.getPassword())
                .build();

        userRepository.save(user);
    }
}
