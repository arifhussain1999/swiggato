package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.request.RestaurantRequest;
import com.example.Swiggato.DTO.response.MenuResponse;
import com.example.Swiggato.DTO.response.RestaurantResponse;

import com.example.Swiggato.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {

    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest){
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .contactNo(restaurantRequest.getContactNo())
                .location(restaurantRequest.getLocation())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .open(true)
                .menuItemList(new ArrayList<>())
                .orderEntities(new ArrayList<>())
                .build();
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant){


        List<MenuResponse> foodResponseList = restaurant.getMenuItemList()
                .stream().map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .contactNo(restaurant.getContactNo())
                .open(restaurant.isOpen())
                .location(restaurant.getLocation())
                .menu(foodResponseList)
                .build();
    }
}
