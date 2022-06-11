package ru.itis.headhunter.dto.pages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.CVDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CVPageDto {
    private List<CVDto> cv;
    private Integer totalPages;
}
