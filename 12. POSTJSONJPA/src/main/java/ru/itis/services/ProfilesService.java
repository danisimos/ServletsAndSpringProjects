package ru.itis.services;

import ru.itis.dto.ProfileDto;
import ru.itis.dto.ProfileForm;
import ru.itis.model.Profile;

import java.util.List;

public interface ProfilesService {
    public List<ProfileDto> getUsers();
    public Profile saveUser(ProfileForm profileForm);
}
