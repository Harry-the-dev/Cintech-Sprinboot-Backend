package com.cintech.PriceJuxtapose.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "pick_n_pay", indexes = {
        @Index(name = "prod_id", columnList = "prod_id", unique = true)
})
@DynamicInsert
@DynamicUpdate
public class PickNPay {
    @Id
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "url")
    private String url;

    @Column(name = "vitality")
    private Boolean vitality ;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    @JsonIgnore
    private Product product;


}