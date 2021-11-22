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

    public static User to(UserForm userForm) {
        return User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .age(userForm.getAge())
                .build();
    }
}
