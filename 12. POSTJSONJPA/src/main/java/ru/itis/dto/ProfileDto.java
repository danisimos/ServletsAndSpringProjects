package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.model.Pet;
import ru.itis.model.Profile;

import java.util.List;

@Data
@Builder
public class ProfileDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private List<Pet> pets;

    public ProfileDto(Integer id, String firstName, String lastName, Integer age, String email, List<Pet> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.pets = pets;
    }

    public static ProfileDto from(Profile profile) {
        return ProfileDto.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .age(profile.getAge())
                .email(profile.getEmail())
                .pets(profile.getPets())
                .build();
    }

    public static Profile to(ProfileDto profileDto) {
        return Profile.builder()
                .id(profileDto.getId())
                .firstName(profileDto.getFirstName())
                .lastName(profileDto.getLastName())
                .age(profileDto.getAge())
                .email(profileDto.getEmail())
                .pets(profileDto.getPets())
                .build();
    }
}
