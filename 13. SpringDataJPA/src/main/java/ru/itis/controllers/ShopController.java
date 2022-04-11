package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.CategoryDto;
import ru.itis.dto.ProductDto;
import ru.itis.dto.ProfileDto;
import ru.itis.models.Category;
import ru.itis.models.Profile;
import ru.itis.services.CategoryService;
import ru.itis.services.ProductService;
import ru.itis.services.ProfileService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShopController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProfileService profileService;


    @GetMapping(value = "/categories")
    public List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping(value =  "/categories")
    public CategoryDto saveCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping(value = "/categories/{category-id}/products")
    public List<ProductDto> getProductsByCategory(@PathVariable("category-id") Integer categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping(value = "/categories/{category-id}/products")
    public ProductDto saveProduct(@PathVariable("category-id") Integer categoryId, @RequestBody ProductDto productDto) {
        return productService.saveProduct(categoryId, productDto);
    }

    @GetMapping(value = "/profiles")
    public List<ProfileDto> getProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping(value = "/profiles/{profile-id}/categories")
    public List<CategoryDto> getCategoriesByProfile(@PathVariable("profile-id") Integer profileId) {
        return categoryService.getCategoriesByProfile(profileId);
    }

    @PostMapping(value = "/profiles")
    public ProfileDto saveProfile(@RequestBody Profile profile, @RequestParam String s) {
        return profileService.saveProfile(profile);
    }
}
