package ru.itis.headhunter.dto.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import ru.itis.headhunter.dto.AccountDto;
import ru.itis.headhunter.dto.CompanyDto;
import ru.itis.headhunter.dto.pages.AccountsPageDto;
import ru.itis.headhunter.dto.pages.CompaniesPageDto;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.models.Company;

import java.util.List;

@Mapper
public abstract class CompanyMapper {
    public abstract CompanyDto toCompanyDto(Company company);
    public abstract Company toCompany(CompanyDto companyDto);
    public abstract List<CompanyDto> toCompanyDtoList(List<Company> companies);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateCompanyFromDto(CompanyDto companyDto, @MappingTarget Company company);

    public CompaniesPageDto toCompaniesPageDto(Page<Company> companies) {
        return CompaniesPageDto.builder()
                .companies(toCompanyDtoList(companies.getContent()))
                .totalPages(companies.getTotalPages())
                .build();
    }
}
