package com.example.Swiggato.Exception;

public class FoodNotFoundException extends RuntimeException{

    public FoodNotFoundException(String message){
        super(message);
    }
}
