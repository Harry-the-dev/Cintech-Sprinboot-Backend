package com.cintech.PriceJuxtapose.repository;

import com.cintech.PriceJuxtapose.entity.PickNPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PickNPayRepository extends JpaRepository<PickNPay, Integer> {
    PickNPay findPickNPayById(Integer id);
    List<PickNPay> findAllByPriceBetween ( double min , double max );

    @Query( value = "select * from pick_n_pay p where p.prod_id = :id" , nativeQuery = true)
    PickNPay findPickNPayByProductId(Integer id);

}