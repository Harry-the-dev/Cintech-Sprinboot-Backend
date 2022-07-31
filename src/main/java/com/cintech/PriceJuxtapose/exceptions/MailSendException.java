package com.cintech.PriceJuxtapose.exceptions;

public class MailSendException extends RuntimeException{
    public MailSendException (String exMessage) {
        super(exMessage);
    }
}
