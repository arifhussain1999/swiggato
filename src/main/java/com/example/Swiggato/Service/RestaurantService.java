package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.request.FoodRequest;
import com.example.Swiggato.DTO.request.RestaurantRequest;
import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.DTO.response.RestaurantResponse;
import com.example.Swiggato.Exception.RestaurantNotFoundException;
import com.example.Swiggato.Model.FoodItem;
import com.example.Swiggato.Model.Restaurant;
import com.example.Swiggato.Repository.RestaurantRepository;
import com.example.Swiggato.Transformers.FoodTransformer;
import com.example.Swiggato.Transformers.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        //    requestDTO -> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // model to responseDTO

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }

    public String updateStatus(int id) {

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();
        restaurant.setOpen(!restaurant.isOpen());

        restaurantRepository.save(restaurant);
        if(restaurant.isOpen()){
            return "Your restaurant is now open for the customers :)";
        }
        return "Your restaurant is now close for the customers :(";
    }

    public RestaurantResponse addFoodToRestaurant(FoodRequest foodRequest) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(foodRequest.getRestaurantId());

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();
        FoodItem foodItem = FoodTransformer.FoodItemRequestToFood(foodRequest);

        foodItem.setRestaurant(restaurant);
        restaurant.getFoodItemList().add(foodItem);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    public List<FoodItemResponse> getMenu(int restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();

        List<FoodItemResponse> foodItemResponses = restaurant.getFoodItemList()
                .stream().map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return foodItemResponses;

    }
}
