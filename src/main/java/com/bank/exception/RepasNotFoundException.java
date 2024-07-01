package com.bank.exception;

public class RepasNotFoundException extends RuntimeException{
    public RepasNotFoundException(){
        super("repas not found !");
    }
}
