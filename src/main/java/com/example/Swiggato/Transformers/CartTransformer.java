package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.response.CartResponse;
import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.Model.Cart;
import java.util.List;
import java.util.stream.Collectors;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){

//        List<FoodItem> foodItems = cart.getFoodItems();
//        List<FoodItemResponse> foodResponse = FoodTransformer.FoodToFoodResponse(foodItems);

        List<FoodItemResponse> foodResponse = cart.getFoodItems()
                .stream().map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodResponseList(foodResponse)
                .build();
    }
}
