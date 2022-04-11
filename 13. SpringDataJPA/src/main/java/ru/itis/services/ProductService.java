package ru.itis.services;

import ru.itis.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProductsByCategory(Integer categoryId);
    ProductDto saveProduct(Integer categoryId, ProductDto productDto);
}
