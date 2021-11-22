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

    public static UserDto from(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .build();
    }

    public static User to(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .build();
    }
}
