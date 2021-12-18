package ru.itis.services;

import ru.itis.dto.UserDto;

import java.util.List;

public interface SearchService {
    List<UserDto> searchUsersByEmail(String email);
}
