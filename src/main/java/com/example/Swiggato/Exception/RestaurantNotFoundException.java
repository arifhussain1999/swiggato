package com.example.Swiggato.Exception;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(String message){
        super(message);
    }
}
