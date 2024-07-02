package com.bank.exception;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(){
        super("transaction not found !");
    }
}
