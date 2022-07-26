package com.cintech.PriceJuxtapose.controller;

import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.service.MainService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class mainController {

    private MainService mainService;

    public mainController(MainService mainService) {
        this.mainService = mainService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/addBulk", consumes = "application/json", produces = "application/json")
    public boolean addWoolworthsProducts(@RequestBody List<MainDTO> mainDTO) {

        mainDTO.forEach(value -> mainService.saveBulk(value));
        return true;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllBulk")
    public List<MainDTO> getPickNPayProducts() {
        return mainService.getAllBulk();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllBulkByTitle/{title}")
    public List<MainDTO> getPickNPayProducts(@PathVariable String title) {
        return mainService.getALLBulkByTitleContainingOrLike(title);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllBulkByPriceBetween/{min}/{max}")
    public List<MainDTO> getAllBulkByPriceBetween(@PathVariable double min,@PathVariable double max ) {
        return mainService.findPriceBetween(min, max);
    }



}



