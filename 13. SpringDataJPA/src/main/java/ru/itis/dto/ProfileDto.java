package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Category;
import ru.itis.models.Product;
import ru.itis.models.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {
    Integer id;
    String firstName;
    String lastName;
    String email;

    public static List<ProfileDto> from(List<Profile> list) {
        return list.stream().map(ProfileDto::from).collect(Collectors.toList());
    }

    public static ProfileDto from(Profile profile) {
        return ProfileDto.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .email(profile.getEmail())
                .build();
    }
}
