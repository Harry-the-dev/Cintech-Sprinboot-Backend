package com.cintech.PriceJuxtapose.repository;

import com.cintech.PriceJuxtapose.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    List<Product>  findAllByProdTitleContainingIgnoreCase (String Title);
    Product findProductById (Integer id ) ;
}