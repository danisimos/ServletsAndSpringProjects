package ru.itis.services;

import ru.itis.dto.SignInForm;
import ru.itis.model.User;

public interface SignInService {
    boolean signIn(SignInForm signInForm);
    User getUser();
}
