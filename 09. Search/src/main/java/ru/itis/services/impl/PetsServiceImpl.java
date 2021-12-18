package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.PetDto;
import ru.itis.model.Pet;
import ru.itis.repositories.PetRepository;
import ru.itis.services.PetsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetsServiceImpl implements PetsService {
    private final PetRepository petRepository;

    @Override
    public List<PetDto> getPets() {
        return petRepository.findAll().stream().map(PetDto::from).toList();
    }

    @Override
    public List<PetDto> addPet(PetDto petDto) {
        petRepository.save(PetDto.to(petDto));
        return getPets();
    }
}
