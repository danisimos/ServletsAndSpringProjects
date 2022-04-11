package ru.itis.services;

import ru.itis.dto.ProfileDto;
import ru.itis.models.Profile;

import java.util.List;

public interface ProfileService {
    List<ProfileDto> getProfiles();
    ProfileDto saveProfile(Profile profile);
}
