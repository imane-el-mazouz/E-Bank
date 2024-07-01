package com.bank.exception;

public class ConseilNotFoundException extends RuntimeException{
    public ConseilNotFoundException(){
        super("conseil not found !");
    }
}
