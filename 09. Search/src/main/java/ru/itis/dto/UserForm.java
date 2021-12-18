package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.model.User;

@Data
@Builder
public class UserForm {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;

    public UserForm(String firstName, String lastName, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public static User to(UserForm userForm) {
        return User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .age(userForm.getAge())
                .email(userForm.getEmail())
                .build();
    }
}
