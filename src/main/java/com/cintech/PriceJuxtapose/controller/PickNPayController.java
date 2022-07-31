package com.cintech.PriceJuxtapose.controller;

import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.service.PickNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/picknpay")
@CrossOrigin("*")
public class PickNPayController {

    @Autowired
    private PickNPayService pickNPayService;


    //https://cintech-springboot-back-end.azurewebsites.net/picknpay/getProducts
    //-----------------------------GET all PNP products------------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProducts")
    public List<MainDTO> getProducts() {
        return pickNPayService.getProducts();
    }


    //https://cintech-springboot-back-end.azurewebsites.net/getProductByTitle
    //-----------------------------GET all PNP products By Title------------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("getProductByTitle/{title}")
    public List<MainDTO> getProductsByTitle(@PathVariable String title) {
        return pickNPayService.getProductByTitle(title);
    }
}
