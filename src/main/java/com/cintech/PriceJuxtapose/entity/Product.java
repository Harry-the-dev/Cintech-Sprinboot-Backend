package com.cintech.PriceJuxtapose.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
@DynamicInsert
@DynamicUpdate
public class Product {
    @Id

    @Column(name = "prod_id", nullable = false)
    private Integer id;

    @Column(name = "prod_volume", nullable = false)
    private Double prodVolume;

    @Column(name = "prod_volume_unit", length = 2)
    private String prodVolumeUnit;

    @Column(name = "prod_title")
    private String prodTitle;

}