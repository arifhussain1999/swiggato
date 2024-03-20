package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.request.FoodItemRequest;
import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.Model.FoodItem;

public class FoodTransformer {

    public static FoodItemResponse FoodToFoodResponse(FoodItem foodItem) {
//        if (foodItems==null){
//           return new ArrayList<>();
//        }
//        List<FoodItemResponse> foodResponseList = new ArrayList<>();
//        for (FoodItem foodItem : foodItems) {
//            FoodItemResponse /foodResponse = FoodItemResponse.builder()
//                    .dishName(foodItem.getDishName())
//                    .cost(foodItem.getCost())
//                    .build();
//
//            foodResponseList.add(foodResponse);
//        }
//        return foodResponseList;

        return FoodItemResponse.builder()
                .dishName(foodItem.getDishName())
                .cost(foodItem.getCost())
                .foodCategory(foodItem.getFoodCategory())
                .veg(foodItem.isVeg())
                .build();
    }

    public static FoodItem FoodItemRequestToFood(FoodItemRequest foodItemRequest){
        return FoodItem.builder()
                .dishName(foodItemRequest.getDishName())
                .foodCategory(foodItemRequest.getFoodCategory())
                .available(foodItemRequest.isAvailable())
                .veg(foodItemRequest.isVeg())
                .cost(foodItemRequest.getCost())
                .build();
    }
}
