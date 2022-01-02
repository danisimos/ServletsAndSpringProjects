package ru.itis.dto;

import lombok.*;
import ru.itis.model.Pet;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class PetDto {
    private Integer id;
    private String kind;
    private String name;

    public static PetDto from(Pet pet) {
        return PetDto.builder()
                .id(pet.getId())
                .kind(pet.getKind())
                .name(pet.getName())
                .build();
    }

    public static Pet to(PetDto petDto) {
        return Pet.builder()
                .id(petDto.id)
                .kind(petDto.getKind())
                .name(petDto.getName())
                .build();
    }
}
