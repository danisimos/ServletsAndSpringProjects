package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.dto.CategoryDto;
import ru.itis.models.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories();
    CategoryDto save(Category category);
    List<CategoryDto> getCategoriesByProfile(Integer profileId);
}
