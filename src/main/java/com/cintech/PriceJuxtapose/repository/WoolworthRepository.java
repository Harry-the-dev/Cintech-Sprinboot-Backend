package com.cintech.PriceJuxtapose.repository;


import com.cintech.PriceJuxtapose.entity.Woolworth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WoolworthRepository extends JpaRepository<Woolworth, Integer> {
    Woolworth findWoolworthsById(Integer id);

    List<Woolworth> findAllByPriceBetween ( double min , double max );

    Woolworth findWoolworthByUrl (String url);

    @Query( value = "select * from woolworths p where p.prod_id = :id" , nativeQuery = true)
    Woolworth findWoolworthsByProductId(Integer id);

}