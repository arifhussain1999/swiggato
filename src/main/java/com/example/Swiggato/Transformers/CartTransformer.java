package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.response.CartResponse;
import com.example.Swiggato.DTO.response.MenuResponse;
import com.example.Swiggato.Model.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){

//        List<MenuItem> menuItems = cart.getMenuItems();
//        List<MenuResponse> foodResponse = FoodTransformer.FoodToFoodResponse(menuItems);


        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodResponseList(new ArrayList<>())
                .build();
    }
}
