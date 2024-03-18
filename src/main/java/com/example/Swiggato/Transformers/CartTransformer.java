package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.response.CartResponse;
import com.example.Swiggato.Model.Cart;

import java.util.ArrayList;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .list(new ArrayList<>())
                .build();
    }
}
