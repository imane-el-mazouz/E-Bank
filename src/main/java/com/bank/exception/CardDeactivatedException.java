package com.bank.exception;

public class CardDeactivatedException extends RuntimeException{
    public CardDeactivatedException(){
        super("card deactivated, please activate your card");
    }
}
