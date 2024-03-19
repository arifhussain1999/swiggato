package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.request.RestaurantRequest;
import com.example.Swiggato.DTO.response.FoodResponse;
import com.example.Swiggato.DTO.response.RestaurantResponse;
import com.example.Swiggato.Model.FoodItem;
import com.example.Swiggato.Model.Restaurant;

import java.util.List;

public class RestaurantTransformer {

    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest){
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .contactNo(restaurantRequest.getContactNo())
                .location(restaurantRequest.getLocation())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .open(true)
                .build();
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant){

        List<FoodItem> foodItems = restaurant.getFoodItemList();

        List<FoodResponse> foodResponseList = FoodTransformer.FoodToFoodResponse(foodItems);

        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .contactNo(restaurant.getContactNo())
                .open(restaurant.isOpen())
                .location(restaurant.getLocation())
                .menu(foodResponseList)
                .build();
    }
}
