package com.cintech.PriceJuxtapose.controller;

import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/all")
@CrossOrigin("*")
public class mainController {

    @Autowired
    private MainService mainService;

    //http://localhost:8080/all/saveProducts
    //----------------------------- Add Products in Bulk to all shops------------------------------------------------
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/saveProducts", consumes = "application/json", produces = "application/json")
    public boolean saveProducts(@RequestBody List<MainDTO> mainDTO) {
        mainDTO.forEach(value -> mainService.saveProducts(value));
        return true;
    }

    //http://localhost:8080/all/start
    //----------------------------- GET starting 10 Random Products From all Stores------------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/start")
    public List<MainDTO> getRandom10() {
        return mainService.getRandom10();
    }


    //http://localhost:8080/all/getProducts
    //----------------------------- GET  Products From all Stores-------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProducts")
    public List<MainDTO> getProducts() {
        return mainService.getProducts();
    }

    //http://localhost:8080/all/getProductsByTitle
    //----------------------------- GET  Products From all Stores-------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProductsByTitle/{title}")
    public List<MainDTO> getProductsByTitle(@PathVariable String title) {
        return mainService.getProductsByTitle(title);
    }

    /*
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllByPriceBetween/{min}/{max}")
    public List<MainDTO> getAllBulkByPriceBetween(@PathVariable double min,@PathVariable double max ) {
        return mainService.findPriceBetween(min, max);
    }*/


}



