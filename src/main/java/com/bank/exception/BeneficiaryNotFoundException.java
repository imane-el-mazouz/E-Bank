package com.bank.exception;

public class BeneficiaryNotFoundException extends RuntimeException{
    public BeneficiaryNotFoundException(){
        super("beneficiary not found !");
    }
}
