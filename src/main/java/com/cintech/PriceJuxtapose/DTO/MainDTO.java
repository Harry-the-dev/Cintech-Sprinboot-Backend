package com.cintech.PriceJuxtapose.DTO;

import com.cintech.PriceJuxtapose.entity.PickNPay;
import com.cintech.PriceJuxtapose.entity.Product;
import com.cintech.PriceJuxtapose.entity.Woolworth;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainDTO implements Serializable {

    private Product product;
    private PickNPay pickNPay;
    private Woolworth woolworths;

}
