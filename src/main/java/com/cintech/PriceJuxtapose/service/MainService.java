package com.cintech.PriceJuxtapose.service;

import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.DTO.PickNPayDTO;
import com.cintech.PriceJuxtapose.DTO.ProductDTO;
import com.cintech.PriceJuxtapose.DTO.WoolworthsDTO;
import com.cintech.PriceJuxtapose.repository.PickNPayRepository;
import com.cintech.PriceJuxtapose.repository.ProductRepository;
import com.cintech.PriceJuxtapose.repository.WoolworthRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MainService {

    private PickNPayService pickNPayService;
    private WoolworthsService woolworthsService;

    private ProductService productService;

    public MainService( PickNPayService pickNPayService, WoolworthsService woolworthsService, ProductService productService) {

        this.pickNPayService = pickNPayService;
        this.woolworthsService = woolworthsService;
        this.productService = productService;
    }

    public List<MainDTO> getALLBulkByTitleContainingOrLike (String Title)
    {
        List<MainDTO> resultDTOList = new ArrayList<MainDTO>();
        List<ProductDTO> productDTOList = productService.getAllProductByTitleLikeOrContaining(Title);
        productDTOList.forEach(value -> resultDTOList.add(assignToMainDTO(value)));
        return resultDTOList;
    }
    public List<MainDTO> getAllBulk ()
    {
        List<MainDTO> resultDTOList = new ArrayList<MainDTO>();
        List<ProductDTO> productDTOList = productService.getAllProductListed();
        productDTOList.forEach(value -> resultDTOList.add(assignToMainDTO(value)));
        return resultDTOList;
    }


    @Transactional
    public boolean saveBulk (MainDTO mainDTO)
    {

        ProductDTO productDTO = ProductDTO.builder()
                .id(mainDTO.getProduct().getId())
                .prodTitle(mainDTO.getProduct().getProdTitle())
                .prodVolume(mainDTO.getProduct().getProdVolume())
                .prodVolumeUnit(mainDTO.getProduct().getProdVolumeUnit())
                .build();

            PickNPayDTO pickNPay = PickNPayDTO.builder()
                    .id(mainDTO.getProduct().getId())
                    .price(mainDTO.getPickNPay().getPrice())
                    .url(mainDTO.getPickNPay().getUrl())
                    .productDTO(productDTO)
                    .build();

        WoolworthsDTO woolworth = WoolworthsDTO.builder()
                .id(mainDTO.getProduct().getId())
                .price(mainDTO.getWoolworths().getPrice())
                .url(mainDTO.getWoolworths().getUrl())
                .productDTO(productDTO)
                .build();

        productService.saveProduct(productDTO);
        woolworthsService.saveProduct(woolworth);
        pickNPayService.saveProduct(pickNPay);

        return true ;
    }

    public static boolean between(double variable, double minValueInclusive, double maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }

    public List<MainDTO> findPriceBetween ( double min , double max )
    {
        List<ProductDTO> productDTOList = productService.getAllProductListed();
        List<MainDTO> result = new ArrayList<>();
        for (ProductDTO value : productDTOList) {
            int id = value.getId();
            if (between(pickNPayService.getProductById(id).getPrice(), min, max) && between(woolworthsService.getProductById(id).getPrice(), min, max)) {
                MainDTO mainDTO = MainDTO.builder()
                        .product(productService.getProductById(id))
                        .pickNPay(pickNPayService.getProductById(id))
                        .woolworths(woolworthsService.getProductById(id))
                        .build();
                result.add(mainDTO);
            } else if (between(pickNPayService.getProductById(id).getPrice(), min, max)) {
                MainDTO mainDTO = MainDTO.builder()
                        .product(productService.getProductById(id))
                        .pickNPay(pickNPayService.getProductById(id))
                        //.woolworths(null)
                        .build();
                result.add(mainDTO);
            } else if (between(woolworthsService.getProductById(id).getPrice(), min, max)) {
                MainDTO mainDTO = MainDTO.builder()
                        .product(productService.getProductById(id))
                        //.pickNPay(null)
                        .woolworths(woolworthsService.getProductById(id))
                        .build();
                result.add(mainDTO);
            }
        }
      return  result;
    }


    public MainDTO assignToMainDTO (ProductDTO productDTO)
    {
        MainDTO mainDTO = MainDTO.builder()
                .product(productDTO)
                .pickNPay(pickNPayService.getProductById(productDTO.getId()))
                .woolworths(woolworthsService.getProductById(productDTO.getId()))
                .build();
        return mainDTO;

    }
    
}
