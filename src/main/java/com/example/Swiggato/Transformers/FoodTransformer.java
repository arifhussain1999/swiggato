package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.response.FoodResponse;
import com.example.Swiggato.Model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class FoodTransformer {

    public static List<FoodResponse> FoodToFoodResponse(List<FoodItem> foodItems) {
        if (foodItems==null){
           return new ArrayList<>();
        }
        List<FoodResponse> foodResponseList = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            FoodResponse foodResponse = FoodResponse.builder()
                    .dishName(foodItem.getDishName())
                    .cost(foodItem.getCost())
                    .build();

            foodResponseList.add(foodResponse);
        }
        return foodResponseList;
    }
}
