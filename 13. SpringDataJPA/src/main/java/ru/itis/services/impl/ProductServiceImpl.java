package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.dto.ProductDto;
import ru.itis.models.Category;
import ru.itis.models.Product;
import ru.itis.repositories.CategoriesRepositoryJpa;
import ru.itis.repositories.ProductsRepositoryJpa;
import ru.itis.services.ProductService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductsRepositoryJpa productsRepositoryJpa;
    private final CategoriesRepositoryJpa categoriesRepositoryJpa;

    @Override
    public List<ProductDto> getProductsByCategory(Integer categoryId) {
        return ProductDto.from(productsRepositoryJpa.findProductsByCategory_Id(categoryId));
    }

    @Override
    public ProductDto saveProduct(Integer categoryId, ProductDto productDto) {
        Category category = categoriesRepositoryJpa.getById(categoryId);
        Product product = Product.builder()
                .title(productDto.getTitle())
                .category(category)
                .build();

        productsRepositoryJpa.save(product);

        return ProductDto.from(product);
    }
}
