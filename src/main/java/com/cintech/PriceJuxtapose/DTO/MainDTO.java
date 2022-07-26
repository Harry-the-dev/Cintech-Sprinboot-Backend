package com.cintech.PriceJuxtapose.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainDTO implements Serializable{

    private ProductDTO product;
    private PickNPayDTO pickNPay;
    private WoolworthsDTO woolworths;

}
