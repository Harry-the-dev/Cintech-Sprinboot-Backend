package com.cintech.PriceJuxtapose.DTO;

import com.cintech.PriceJuxtapose.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WoolworthsDTO implements Serializable {

    @JsonIgnore
    private Product product;
    @JsonIgnore
    private Integer id;
    private Double price;
    private String url;
    private Boolean vitality;

}
