package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.request.FoodItemRequest;
import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.Model.FoodItem;
import com.example.Swiggato.Repository.FoodItemRepository;
import com.example.Swiggato.Transformers.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    @Autowired
    FoodItemRepository foodItemRepository;

    public FoodItemResponse addFoodItem(FoodItemRequest foodItemRequest) {
        FoodItem foodItem = FoodTransformer.FoodItemRequestToFood(foodItemRequest);


    }
}
