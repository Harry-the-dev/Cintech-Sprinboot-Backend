package com.cintech.PriceJuxtapose.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroceryListMail {
    private String subject;
    private String recipient;
    private String body;
}
