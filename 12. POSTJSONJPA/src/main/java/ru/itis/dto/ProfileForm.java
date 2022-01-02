package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.model.Profile;

@Data
@Builder
public class ProfileForm {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;

    public ProfileForm(String firstName, String lastName, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public static Profile to(ProfileForm profileForm) {
        return Profile.builder()
                .firstName(profileForm.getFirstName())
                .lastName(profileForm.getLastName())
                .age(profileForm.getAge())
                .email(profileForm.getEmail())
                .build();
    }
}
