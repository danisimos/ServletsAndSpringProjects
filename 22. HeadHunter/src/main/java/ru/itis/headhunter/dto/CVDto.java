package ru.itis.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.models.Account;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CVDto {
    public enum State {
        ACTIVE, DELETED
    }

    private Long id;
    private String title;
    private String text;
}
