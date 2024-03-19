package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.response.CartResponse;
import com.example.Swiggato.DTO.response.FoodResponse;
import com.example.Swiggato.Model.Cart;
import com.example.Swiggato.Model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){

        List<FoodItem> foodItems = cart.getFoodItems();
        List<FoodResponse> foodResponse = FoodTransformer.FoodToFoodResponse(foodItems);

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodResponseList(foodResponse)
                .build();
    }
}
