package com.cintech.PriceJuxtapose.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDTO implements Serializable {
    private Integer id;
    private Double prodVolume;
    private String prodVolumeUnit;
    private String prodTitle;
}
