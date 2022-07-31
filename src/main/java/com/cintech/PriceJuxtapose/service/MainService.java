package com.cintech.PriceJuxtapose.service;

import com.cintech.PriceJuxtapose.DTO.GroceryListDTO;
import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.entity.PickNPay;
import com.cintech.PriceJuxtapose.entity.Product;
import com.cintech.PriceJuxtapose.entity.Woolworth;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MainService {


    @Autowired
    private PickNPayService pickNPayService;

    @Autowired
    private WoolworthsService woolworthsService;


    @Autowired
    private ProductService productService;


    public List<MainDTO> getProductsByTitle(String Title) {
        List<MainDTO> resultDTOList = new ArrayList<MainDTO>();
        List<Product> productDTOList = productService.getAllProductByTitleLikeOrContaining(Title);
        productDTOList.forEach(value -> resultDTOList.add(assignToMainDTO(value)));

        return resultDTOList;
    }

    public List<MainDTO> getProducts() {
        List<MainDTO> resultDTOList = new ArrayList<MainDTO>();
        List<Product> productList = productService.getAllProductListed();
        productList.forEach(value -> resultDTOList.add(assignToMainDTO(value)));
        return resultDTOList;
    }

    public List<MainDTO> getRandom10() {
        List<MainDTO> resultDTOList = new ArrayList<MainDTO>();
        List<Product> productDTOList = productService.getRandom10();
        productDTOList.forEach(value -> resultDTOList.add(assignToMainDTO(value)));
        return resultDTOList;
    }

    @Transactional
    public boolean saveProducts(MainDTO mainDTO) {

        productService.saveProduct(mainDTO);
        woolworthsService.saveProduct(mainDTO);
        pickNPayService.saveProduct(mainDTO);
        return true;
    }

    public static boolean between(double variable, double minValueInclusive, double maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }

    public MainDTO assignToMainDTO(Product product) {
        MainDTO mainDTO = MainDTO.builder()
                .product(product)
                .pickNPay(pickNPayService.getProductById(product.getId()).getPickNPay())
                .woolworths(woolworthsService.getProductById(product.getId()).getWoolworths())
                .build();
        return mainDTO;

    }


}
