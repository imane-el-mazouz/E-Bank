package com.bank.exception;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(){
        super("card not found !");
    }
}
