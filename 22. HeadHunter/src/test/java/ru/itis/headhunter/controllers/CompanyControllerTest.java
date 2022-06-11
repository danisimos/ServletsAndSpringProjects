package ru.itis.headhunter.controllers;

import org.hibernate.loader.plan.build.internal.returns.AbstractAnyReference;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.headhunter.dto.CompanyDto;
import ru.itis.headhunter.dto.forms.CompanyForm;
import ru.itis.headhunter.dto.pages.CompaniesPageDto;
import ru.itis.headhunter.models.Account;
import ru.itis.headhunter.repositories.AccountsRepository;
import ru.itis.headhunter.security.details.AccountUserDetails;
import ru.itis.headhunter.security.details.AccountUserDetailsService;
import ru.itis.headhunter.security.jwt.JwtProvider;
import ru.itis.headhunter.services.CompanyService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CompanyController.class)
@ActiveProfiles(value = {"test"})
@DisplayName("Company controller is working when:")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    private static final CompanyDto SKURATOV_COMPANY = CompanyDto.builder()
            .id(1L)
            .name("Skuratov")
            .description("Skuratov coffee")
            .build();

    private static final CompanyDto STARBUCKS_COMPANY = CompanyDto.builder()
            .id(2L)
            .name("Starbucks")
            .description("Starbucks coffee")
            .build();

    private static final List<CompanyDto> COMPANIES_LIST = Arrays.asList(SKURATOV_COMPANY, STARBUCKS_COMPANY);

    @BeforeEach
    public void setUp() {
        when(companyService.getAllCompanies(0L, Optional.empty())).thenReturn(CompaniesPageDto.builder()
                .companies(COMPANIES_LIST)
                .totalPages(1)
                .build());

        when(companyService.createCompany(CompanyForm.builder()
                .name("some name")
                .description("some description")
                .build()))
                .thenReturn(CompanyDto.builder()
                        .id(3L)
                        .name("name")
                        .description("description").build());
    }

    @Test
    public void return_companies_on_0_page() throws Exception {
        mockMvc.perform(get("/api/headhunter/companies/")
                    .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("companies", hasSize(2)))
                .andExpect(jsonPath("totalPages", is(1)))
                .andExpect(jsonPath("companies[0].id", is(1)))
                .andExpect(jsonPath("companies[1].id", is(2)));
    }

    @Test
    public void return_400_when_add_company_with_blank_name() throws Exception {
        mockMvc.perform(post("/api/headhunter/companies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"\",\n" +
                        "  \"description\": \"some description\"\n" +
                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void add_company_with_valid_name() throws Exception {
        mockMvc.perform(post("/api/headhunter/companies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"some name\",\n" +
                        "  \"description\": \"some description\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(3)))
                .andExpect(jsonPath("name", is("name")))
                .andExpect(jsonPath("description", is("description")))
                .andDo(print());
    }

    @Test
    public void add_company_with_invalid_name_with_forbidden_words() throws Exception {
        mockMvc.perform(post("/api/headhunter/companies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"bad\",\n" +
                        "  \"description\": \"bad\"\n" +
                        "}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
