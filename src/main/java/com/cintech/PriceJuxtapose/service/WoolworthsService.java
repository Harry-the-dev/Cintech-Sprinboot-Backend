package com.cintech.PriceJuxtapose.service;

import com.cintech.PriceJuxtapose.DTO.*;

import com.cintech.PriceJuxtapose.entity.PickNPay;
import com.cintech.PriceJuxtapose.entity.Product;
import com.cintech.PriceJuxtapose.entity.Woolworth;
import com.cintech.PriceJuxtapose.repository.WoolworthRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WoolworthsService {

    @Autowired
    private WoolworthRepository woolworthRepository;
    @Autowired
    private ProductService productService;
    private ModelMapper mapper;


    public Woolworth convertMainDTOtoEntity(MainDTO mainDTO) {
        Woolworth woolworths = Woolworth.builder()
                .id(mainDTO.getProduct().getId())
                .price(mainDTO.getWoolworths().getPrice())
                .url(mainDTO.getWoolworths().getUrl())
                .vitality(mainDTO.getWoolworths().getVitality())
                .product(mainDTO.getProduct())
                .build();


        return woolworths;
    }

    public MainDTO convertEntityToMainDTO(Woolworth woolworths) {
        Product product = Product.builder()
                .id(woolworths.getProduct().getId())
                .prodTitle(woolworths.getProduct().getProdTitle())
                .prodVolume(woolworths.getProduct().getProdVolume())
                .prodVolumeUnit(woolworths.getProduct().getProdVolumeUnit())
                .build();

        Woolworth woolworthTemp = Woolworth.builder()
                .id(woolworths.getProduct().getId())
                .price(woolworths.getPrice())
                .url(woolworths.getUrl())
                .vitality(woolworths.getVitality())
                .product(product)
                .build();


        MainDTO mainDTO = MainDTO.builder()
                .product(product)
                .woolworths(woolworthTemp)
                .build();

        return mainDTO;
    }

    public MainDTO getProductById(Integer id) {
        return convertEntityToMainDTO(woolworthRepository.findWoolworthsByProductId(id));
    }

    public List<MainDTO> getProductByTitle(String title) {
        List<MainDTO> result = new ArrayList<MainDTO>();
        List<Product> productDTOList = productService.getAllProductByTitleLikeOrContaining(title);
        productDTOList.forEach(item -> result.add(getProductById(item.getId())));
        return result;
    }

    public Woolworth getWoolworthByUrl (String url)
    {
        Woolworth result =  woolworthRepository.findWoolworthByUrl(url);
        if (result == null) {
            return null;
        }
        return result ;
    }


    public List<MainDTO> getProducts() {
        List<MainDTO> result = new ArrayList<MainDTO>();
        woolworthRepository.findAll().forEach(value -> result.add(convertEntityToMainDTO(value)));
        return result;
    }

    public Woolworth saveProduct(MainDTO mainDTO) {
        return woolworthRepository.save(convertMainDTOtoEntity(mainDTO));
    }


}