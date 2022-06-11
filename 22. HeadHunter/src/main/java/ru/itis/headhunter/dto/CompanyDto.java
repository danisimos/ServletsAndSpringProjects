package ru.itis.headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.models.Company;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {
    public enum State {
        ACTIVE, DELETED
    }

    private Long id;
    private String name;
    private String description;
}
