package com.cintech.PriceJuxtapose;

import com.cintech.PriceJuxtapose.DTO.PickNPayDTO;
import com.cintech.PriceJuxtapose.DTO.ProductDTO;
import com.cintech.PriceJuxtapose.DTO.WoolworthsDTO;
import com.cintech.PriceJuxtapose.repository.PickNPayRepository;
import com.cintech.PriceJuxtapose.service.PickNPayService;
import com.cintech.PriceJuxtapose.service.ProductService;
import com.cintech.PriceJuxtapose.service.WoolworthsService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PriceJuxtaposeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ProductService productService;
	@Autowired
	private PickNPayService pickNPayService;

	@Autowired
	private WoolworthsService woolworthsService;
	@Autowired
	private PickNPayRepository pickNPayRepository;

	private ModelMapper mapper;
	/*
	@Test
	public void saveAllProduct6() {

		ProductDTO product = ProductDTO.builder()
				.id(1)
				.prodTitle("Jungle Oats 1kg")
				.prodVolume(200.00)
				.prodVolumeUnit("g")
				.build();

		PickNPayDTO pickNPay = PickNPayDTO.builder()
				.id(1)
				.price(29.99)
				.url("https://www.pnp.co.za/pnpstorefront/pnp/en/All-Products/Food-Cupboard/Breakfast-Cereals-%26-Bars/Hot-Cereals/Jungle-Oats-1kg/p/000000000000253187_EA")
				.productDTO(product)
				.build();

		WoolworthsDTO woolworth = WoolworthsDTO.builder()
				.id(1)
				.price(29.99)
				.url("https://www.woolworths.co.za/prod/Food/Pantry/Breakfast-Cereals-Bars/Porridge-Oats/Jungle-Oats-1-kg/_/A-6001275000003")
				.productDTO(product)
				.build();

		productService.saveProduct(product);
		pickNPayService.saveProduct(pickNPay);
		woolworthsService.saveProduct(woolworth);

	}*/

}
