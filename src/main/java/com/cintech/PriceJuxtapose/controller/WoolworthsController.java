package com.cintech.PriceJuxtapose.controller;

import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.service.WoolworthsService;
import com.cintech.PriceJuxtapose.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/woolworths")
public class WoolworthsController {

    @Autowired
    private  WoolworthsService woolworthsService;

    //http://localhost:8080/woolworths/getProducts
    //-----------------------------GET all Woolworths products------------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProducts")
    public List<MainDTO> getProducts() {
        return woolworthsService.getProducts();
    }

    //http://localhost:8080/woolworths/getProductsByTitle
    //-----------------------------GET all Woolworths products By Title------------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("getProductsByTitle/{title}")
    public List<MainDTO>getProductsByTitle(@PathVariable String title) {
        return woolworthsService.getProductByTitle(title) ;
    }



}
