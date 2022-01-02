package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProfileDto;
import ru.itis.repositories.ProfileRepository;
import ru.itis.services.SearchService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final ProfileRepository profileRepository;

    @Override
    public List<ProfileDto> searchUsersByEmail(String email) {
        return profileRepository.findByEmail(email).stream().map(ProfileDto::from).collect(Collectors.toList());
    }
}
