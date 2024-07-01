package com.bank.exception;

public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(){
        super("report not found !");
    }
}
