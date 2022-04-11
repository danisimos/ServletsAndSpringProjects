package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Category;
import ru.itis.models.Product;
import ru.itis.models.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;
    private String title;
    private String description;

    public static List<CategoryDto> from(List<Category> list) {
        return list.stream().map(CategoryDto::from).collect(Collectors.toList());
    }

    public static CategoryDto from(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    public static Category to(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .title(categoryDto.getTitle())
                .description(categoryDto.getDescription())
                .build();
    }
}
