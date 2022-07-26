package com.cintech.PriceJuxtapose.DTO;

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
    private Integer id;
    private Double price;
    private String url;
    @JsonIgnore
    private ProductDTO productDTO;
}
