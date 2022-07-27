package com.cintech.PriceJuxtapose.repository;

import com.cintech.PriceJuxtapose.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    List<Product> findAllByProdTitleContainingIgnoreCase(String Title);
    Product findProductById(Integer id);
    @Query(value = "SELECT * FROM products ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Product> findRandom10();
}
