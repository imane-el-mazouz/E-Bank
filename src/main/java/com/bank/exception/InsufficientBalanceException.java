package com.bank.exception;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(){
        super("insufficient balance !");
    }
}
