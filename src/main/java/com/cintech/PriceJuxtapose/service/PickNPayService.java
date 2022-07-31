package com.cintech.PriceJuxtapose.service;

import com.cintech.PriceJuxtapose.DTO.MainDTO;
import com.cintech.PriceJuxtapose.entity.PickNPay;
import com.cintech.PriceJuxtapose.entity.Product;
import com.cintech.PriceJuxtapose.repository.PickNPayRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PickNPayService {


    @Autowired
    private PickNPayRepository pickNPayRepository;
    @Autowired
    private ProductService productService;


    private ModelMapper mapper;


    public PickNPay convertMainDTOtoEntity(MainDTO mainDTO) {
        PickNPay pickNPay = PickNPay.builder()
                .id(mainDTO.getProduct().getId())
                .price(mainDTO.getPickNPay().getPrice())
                .url(mainDTO.getPickNPay().getUrl())
                .vitality(mainDTO.getPickNPay().getVitality())
                .product(mainDTO.getProduct()).build();
        return pickNPay;
    }

    public MainDTO convertEntityToMainDTO(PickNPay pickNPay) {
        Product product = Product.builder().id(pickNPay.getProduct().getId()).prodTitle(pickNPay.getProduct().getProdTitle()).prodVolume(pickNPay.getProduct().getProdVolume()).prodVolumeUnit(pickNPay.getProduct().getProdVolumeUnit()).build();

        PickNPay pickNPayTemp = PickNPay.builder()
                .id(pickNPay.getProduct().getId())
                .price(pickNPay.getPrice())
                .url(pickNPay.getUrl())
                .vitality(pickNPay.getVitality())
                .product(pickNPay.getProduct()).build();

        MainDTO mainDTO = MainDTO.builder().product(product).pickNPay(pickNPayTemp).build();

        return mainDTO;
    }

    public MainDTO getProductById(Integer id) {
        return convertEntityToMainDTO(pickNPayRepository.findPickNPayByProductId(id));
    }

    public PickNPay getPickNpayByUrl (String url)
    {
      PickNPay result =  pickNPayRepository.findPickNPayByUrl(url);
      if (result == null) {
          return null;
      }
      return result ;
    }

    public List<MainDTO> getProductByTitle(String title) {
        List<MainDTO> result = new ArrayList<MainDTO>();
        List<Product> productDTOList = productService.getAllProductByTitleLikeOrContaining(title);
        productDTOList.forEach(item -> result.add(getProductById(item.getId())));
        return result;
    }


    public List<MainDTO> getProducts() {
        List<MainDTO> result = new ArrayList<MainDTO>();
        pickNPayRepository.findAll().forEach(value -> result.add(convertEntityToMainDTO(value)));
        return result;
    }

    public PickNPay saveProduct(MainDTO mainDTO) {
        return pickNPayRepository.save(convertMainDTOtoEntity(mainDTO));
    }

}
