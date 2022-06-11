package ru.itis.headhunter.services;

import ru.itis.headhunter.dto.CVDto;
import ru.itis.headhunter.dto.forms.CVForm;
import ru.itis.headhunter.dto.pages.CVPageDto;

import java.util.List;
import java.util.Optional;

public interface CVService {
    CVDto createCV(CVForm cvForm);

    CVPageDto getAllCV(Long page, Optional<String> sortBy);
}
