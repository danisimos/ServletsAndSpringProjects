package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProfileDto;
import ru.itis.models.Profile;
import ru.itis.repositories.ProductsRepositoryJpa;
import ru.itis.repositories.ProfilesRepositoryJpa;
import ru.itis.services.ProfileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private  final ProfilesRepositoryJpa profilesRepositoryJpa;

    @Override
    public List<ProfileDto> getProfiles() {
        return ProfileDto.from(profilesRepositoryJpa.findAll());
    }

    @Override
    public ProfileDto saveProfile(Profile profile) {
        return ProfileDto.from(profilesRepositoryJpa.save(profile));
    }
}
