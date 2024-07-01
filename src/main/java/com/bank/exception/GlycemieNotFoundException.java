package com.bank.exception;

public class GlycemieNotFoundException extends RuntimeException{
    public GlycemieNotFoundException(){
        super("glycemie not found !");
    }
}
