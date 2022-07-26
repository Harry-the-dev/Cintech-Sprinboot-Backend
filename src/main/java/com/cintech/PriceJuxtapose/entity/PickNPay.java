package com.cintech.PriceJuxtapose.entity;

import lombok.*;

import javax.persistence.*;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pick_n_pay", indexes = {
        @Index(name = "prod_id", columnList = "prod_id", unique = true)
})
public class PickNPay {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "url")
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    private Product product;


}