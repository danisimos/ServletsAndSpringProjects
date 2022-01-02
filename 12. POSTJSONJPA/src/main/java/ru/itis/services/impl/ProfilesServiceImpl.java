package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProfileDto;
import ru.itis.dto.ProfileForm;
import ru.itis.model.Profile;
import ru.itis.repositories.ProfileRepository;
import ru.itis.services.ProfilesService;

import java.util.List;

@Service
public class ProfilesServiceImpl implements ProfilesService {
    ProfileRepository profileRepository;

    @Autowired
    public ProfilesServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProfileDto> getUsers() {
        return profileRepository.findAll().stream().map(ProfileDto::from).toList();
    }

    @Override
    public Profile saveUser(ProfileForm profileForm) {
        return profileRepository.save(ProfileForm.to(profileForm));
    }
}
