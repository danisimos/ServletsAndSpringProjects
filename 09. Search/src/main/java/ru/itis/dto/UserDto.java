package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.model.User;

@Data
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;

    public UserDto(String firstName, String lastName, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public static UserDto from(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .build();
    }

    public static User to(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .build();
    }
}
