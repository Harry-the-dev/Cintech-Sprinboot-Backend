package com.cintech.PriceJuxtapose.service;

import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.DTO.ProductDTO;
import com.cintech.PriceJuxtapose.entity.Product;
import com.cintech.PriceJuxtapose.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ModelMapper mapper;
    @Autowired
    private ProductRepository productRepository;


    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product convertDTOtoEntity(ProductDTO productDTO) {
        this.mapper = new ModelMapper();
        Product product = this.mapper.map(productDTO, Product.class);
        return product;
    }

    public ProductDTO convertEntityToDTO(Product product) {
        this.mapper = new ModelMapper();
        ProductDTO productDTO = this.mapper.map(product, ProductDTO.class);
        return productDTO;
    }


    public ProductDTO getProductById(Integer id) {
        return convertEntityToDTO(productRepository.findProductById(id));
    }

    public List<Product> getAllProductListed() {
        List<Product> result = new ArrayList<Product>();
        productRepository.findAll().forEach(value -> result.add(value));
        return result;
    }

    public List<Product> getRandom10() {
        List<Product> result = new ArrayList<Product>();
        productRepository.findRandom10().forEach(value -> result.add(value));
        return result;

    }

    public List<Product> getAllProductByTitleLikeOrContaining(String Title) {
        List<Product> result = new ArrayList<Product>();
        productRepository.findAllByProdTitleContainingIgnoreCase(Title).forEach(value -> result.add(value));
        return result;
    }

    public Product saveProduct(MainDTO mainDTO) {

        return productRepository.save(mainDTO.getProduct());
    }


}
