package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Account;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long id;

    private String login;

    private String firstName;
    private String lastName;

    private String email;

    private Account.State state;

    private FileLinkDto avatarLink;
}
