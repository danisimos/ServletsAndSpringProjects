package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.UserDto;
import ru.itis.repositories.UserRepository;
import ru.itis.services.SearchService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> searchUsersByEmail(String email) {
        return userRepository.findByEmail(email).stream().map(UserDto::from).collect(Collectors.toList());
    }
}
