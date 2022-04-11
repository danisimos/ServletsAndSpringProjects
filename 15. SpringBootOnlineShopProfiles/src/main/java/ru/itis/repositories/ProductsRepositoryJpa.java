package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Product;

import java.util.List;

@Repository
public interface ProductsRepositoryJpa extends JpaRepository<Product, Integer> {
    Product save(Product product);
    List<Product> findProductsByCategory_Id(Integer categoryId);
}
