package com.bank.exception;

public class AccountIsClosedException extends RuntimeException{
    public AccountIsClosedException(){
        super("account is closed, please contact support !");
    }
}
