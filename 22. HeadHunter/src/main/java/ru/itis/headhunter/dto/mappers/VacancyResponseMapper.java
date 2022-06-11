package ru.itis.headhunter.dto.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.VacancyResponseDto;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.models.VacancyResponse;

import java.util.List;

@Mapper
public abstract class VacancyResponseMapper {
    public abstract VacancyResponseDto toVacancyResponseDto(VacancyResponse vacancyResponse);
    public abstract VacancyResponse toVacancyResponse(VacancyResponseDto vacancyResponseDto);
    public abstract List<VacancyResponseDto> toVacancyResponseDtoList(List<VacancyResponse> vacancyResponses);

    @AfterMapping
    protected void setId(VacancyResponse vacancyResponse, @MappingTarget VacancyResponseDto vacancyResponseDto){
        vacancyResponseDto.setVacancyId(vacancyResponse.getVacancy().getId());
        vacancyResponseDto.setAccountId(vacancyResponse.getAccount().getId());
    }
}
