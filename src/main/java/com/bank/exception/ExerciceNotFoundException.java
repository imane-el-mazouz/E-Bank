package com.bank.exception;

public class ExerciceNotFoundException extends RuntimeException{
    public ExerciceNotFoundException(){
        super("Exercice not found !");
    }
}
