package com.cintech.PriceJuxtapose.service;

import com.cintech.PriceJuxtapose.DTO.GroceryListDTO;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ComparisonServiceTest {

    @Autowired
    private ComparisonService comparisonService;

    @Test
    public void test ()
    {

        List<GroceryListDTO> list = new ArrayList<>();
        GroceryListDTO gl = GroceryListDTO.builder()
                .url("https://www.pnp.co.za/pnpstorefront/pnp/en/Jacobs-Kronung-Instant-Coffee-200g/p/000000000000162106_EA")
                .build();

        GroceryListDTO gl1 = GroceryListDTO.builder()
                .url("https://www.pnp.co.za/pnpstorefront/pnp/en/Nescafe-Classic-Coffee-200g/p/000000000000539178_EA")
                .build();
        GroceryListDTO gl2 = GroceryListDTO.builder()
                .url("https://www.pnp.co.za/pnpstorefront/pnp/en/All-Products/Food-Cupboard/Breakfast-Cereals-%26-Bars/Hot-Cereals/Jungle-Oats-1kg/p/000000000000253187_EA")
                .build();
        GroceryListDTO gl3 = GroceryListDTO.builder()
                .url("https://www.woolworths.co.za/prod/Food/Milk-Dairy-Eggs/Milk/Fresh-Milk/Organic-Fresh-Low-Fat-Milk-2-L/_/A-6001009020437?isFromPLP=true")
                .build();
        GroceryListDTO gl4 = GroceryListDTO.builder()
                .url("https://www.woolworths.co.za/prod/Food/Pantry/Snacks-Chips-Nuts/Chips-Dips/Potato-Chips/Lay-s-Sweet-and-Smokey-American-BBQ-Potato-Chips-120-g/_/A-6009710720653?isFromPLP=true")
                .build();
        GroceryListDTO gl5 = GroceryListDTO.builder()
                .url("https://www.woolworths.co.za/prod/Food/Fruit-Vegetables-Salads/Fresh-Fruit/Bananas/Bananas-1-2-kg/_/A-20109608?isFromPLP=true")
                .build();
        GroceryListDTO gl6 = GroceryListDTO.builder()
                .url("https://www.woolworths.co.za/prod/Food/Household-Cleaning/Household/Toilet-Paper-Tissues-Wipes/Toilet-Paper/Bulk-Super-Soft-2-Ply-Toilet-Paper-18-pk/_/A-6005000252309?isFromPLP=true")
                .build();
        GroceryListDTO gl7 = GroceryListDTO.builder()
                .url("https://www.woolworths.co.za/prod/Food/Milk-Dairy-Eggs/Milk/Fresh-Milk/Organic-Fresh-Low-Fat-Milk-2-L/_/A-6001009020437?isFromPLP=true")
                .build();



        list.add(gl);
        list.add(gl1);
        list.add(gl2);
        list.add(gl3);
        list.add(gl4);

        comparisonService.processGroceryList(list,"29hgod@gmail.com");
    }

}