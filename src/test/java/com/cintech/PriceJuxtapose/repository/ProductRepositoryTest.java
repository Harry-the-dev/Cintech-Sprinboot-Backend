package com.cintech.PriceJuxtapose.repository;

import com.cintech.PriceJuxtapose.service.PickNPayService;
import com.cintech.PriceJuxtapose.service.ProductService;
import com.cintech.PriceJuxtapose.service.WoolworthsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private PickNPayService pickNPayService;

    @Autowired
    private WoolworthsService woolworthsService;
    @Autowired
    private PickNPayRepository pickNPayRepository;

    private ModelMapper mapper;

}