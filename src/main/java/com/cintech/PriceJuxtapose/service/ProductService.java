package com.cintech.PriceJuxtapose.service;

import com.cintech.PriceJuxtapose.DTO.ProductDTO;
import com.cintech.PriceJuxtapose.entity.Product;
import com.cintech.PriceJuxtapose.repository.PickNPayRepository;
import com.cintech.PriceJuxtapose.repository.ProductRepository;
import com.cintech.PriceJuxtapose.repository.WoolworthRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ModelMapper mapper;
    private ProductRepository productRepository;



    public ProductService(ProductRepository productRepository, PickNPayRepository pickNPayRepository, WoolworthRepository woolworthRepository) {
        this.productRepository = productRepository;

    }

    public List<Product> getAll ()
    {
        return productRepository.findAll();
    }

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


    public ProductDTO getProductById (Integer id ) {
        return convertEntityToDTO(productRepository.findProductById(id ));
    }

    public List<ProductDTO> getAllProductListed() {
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        productRepository.findAll().forEach(value -> result.add(convertEntityToDTO(value)));
        return result;
    }
    public List<ProductDTO> getAllProductByTitleLikeOrContaining(String Title) {
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        productRepository.findAllByProdTitleContainingIgnoreCase(Title).forEach(value -> result.add(convertEntityToDTO(value)));
        return result;
    }

    public Product saveProduct(ProductDTO product) {
        Product result = convertDTOtoEntity(product);
        return productRepository.save(result);
    }




}
