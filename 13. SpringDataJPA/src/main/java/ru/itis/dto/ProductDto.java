package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Category;
import ru.itis.models.Product;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    Integer id;
    String title;
    Integer categoryId;

    public static List<ProductDto> from(List<Product> list) {
        return list.stream().map(ProductDto::from).collect(Collectors.toList());
    }

    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .categoryId(product.getCategory().getId())
                .build();
    }
}
