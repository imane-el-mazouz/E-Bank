package com.bank.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(){
        super("account not found !");
    }
}
