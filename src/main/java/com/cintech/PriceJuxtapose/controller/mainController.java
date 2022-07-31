package com.cintech.PriceJuxtapose.controller;

import com.cintech.PriceJuxtapose.DTO.GroceryListDTO;
import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.service.ComparisonService;
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

    @Autowired
    private ComparisonService comparisonService;

    //https://cintech-springboot-back-end.azurewebsites.net/all/saveProducts
    //----------------------------- Add Products in Bulk to all shops------------------------------------------------
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/saveProducts", consumes = "application/json", produces = "application/json")
    public boolean saveProducts(@RequestBody List<MainDTO> mainDTO) {
        mainDTO.forEach(value -> mainService.saveProducts(value));
        return true;
    }

    //https://cintech-springboot-back-end.azurewebsites.net/all/start
    //----------------------------- GET starting 10 Random Products From all Stores------------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/start")
    public List<MainDTO> getRandom10() {
        return mainService.getRandom10();
    }


    //https://cintech-springboot-back-end.azurewebsites.net/all/getProducts
    //----------------------------- GET  Products From all Stores-------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProducts")
    public List<MainDTO> getProducts() {
        return mainService.getProducts();
    }

    //https://cintech-springboot-back-end.azurewebsites.net/all/getProductsByTitle
    //----------------------------- GET  Products From all Stores-------------------------------------------
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProductsByTitle/{title}")
    public List<MainDTO> getProductsByTitle(@PathVariable String title) {
        return mainService.getProductsByTitle(title);
    }

    //https://cintech-springboot-back-end.azurewebsites.net/all/processGroceryList
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/processGroceryList/{email}")
    public void saveGroceryList ( @PathVariable String email , @RequestBody List<GroceryListDTO> groceryListDTO)
    {
        comparisonService.processGroceryList(groceryListDTO, email);
    }



}



