package ru.itis.services;

import ru.itis.dto.PetDto;
import ru.itis.model.Pet;

import java.util.List;

public interface PetsService {
    List<PetDto> getPets();
    List<PetDto> addPet(PetDto petDto);
}
