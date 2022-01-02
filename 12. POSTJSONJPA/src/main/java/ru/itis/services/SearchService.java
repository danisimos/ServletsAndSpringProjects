package ru.itis.services;

import ru.itis.dto.ProfileDto;

import java.util.List;

public interface SearchService {
    List<ProfileDto> searchUsersByEmail(String email);
}
