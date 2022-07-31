package com.cintech.PriceJuxtapose.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationEmail {
    private String subject;
    private String recipient ;
    private String body;

}
