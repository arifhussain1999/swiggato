package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.Exception.RestaurantNotFoundException;
import com.example.Swiggato.Model.FoodItem;
import com.example.Swiggato.Model.Restaurant;
import com.example.Swiggato.Repository.FoodItemRepository;
import com.example.Swiggato.Repository.RestaurantRepository;
import com.example.Swiggato.Transformers.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemService {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<FoodItemResponse> vegFoods(int restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();

        List<FoodItemResponse> vegFoodResponses = restaurant.getFoodItemList()
                .stream().filter(foodItem -> foodItem.isVeg())
                .map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return vegFoodResponses;
    }
}
