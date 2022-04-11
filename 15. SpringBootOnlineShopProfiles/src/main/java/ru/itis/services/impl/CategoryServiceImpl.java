package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Service;
import ru.itis.dto.CategoryDto;
import ru.itis.models.Category;
import ru.itis.models.Profile;
import ru.itis.repositories.CategoriesRepositoryJpa;
import ru.itis.repositories.ProductsRepositoryJpa;
import ru.itis.repositories.ProfilesRepositoryJpa;
import ru.itis.services.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoriesRepositoryJpa categoriesRepositoryJpa;
    private final ProfilesRepositoryJpa profilesRepositoryJpa;

    @Override
    public List<CategoryDto> getCategories() {
        return CategoryDto.from(categoriesRepositoryJpa.findAll());
    }

    @Override
    public CategoryDto save(Category category) {
        categoriesRepositoryJpa.save(category);

        return CategoryDto.from(category);
    }

    @Override
    public List<CategoryDto> getCategoriesByProfile(Integer profileId) {
        Profile profile = profilesRepositoryJpa.getById(profileId);

        return CategoryDto.from(categoriesRepositoryJpa.findAllByProfilesContains(profile));
    }
}
