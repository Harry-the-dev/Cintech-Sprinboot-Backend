package com.cintech.PriceJuxtapose.controller;

import com.cintech.PriceJuxtapose.DTO.PickNPayDTO;
import com.cintech.PriceJuxtapose.service.PickNPayService;
import com.cintech.PriceJuxtapose.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class PickNPayController {

    private final PickNPayService pickNPayService ;
    private final ProductService productService ;

    public PickNPayController(PickNPayService pickNPayService, ProductService productService) {
        this.pickNPayService = pickNPayService;
        this.productService = productService;
    }

    //http://localhost:8080/test/getPickNPayProducts
    //-----------------------------GET all PNP products------------------------------------------------
    @GetMapping("/getPickNPayProducts")
    public List<PickNPayDTO> getPickNPayProducts() {
        return pickNPayService.getALL();
    }


    //http://localhost:8080/test/getPickNPayProductById
    //-----------------------------GET all PNP products By ID------------------------------------------------
    @GetMapping("/getPickNPayProducts/{id}")
    public PickNPayDTO getALLPickNPayProductById(@PathVariable Integer id) {
        return pickNPayService.getProductById(id);
    }
}
