package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Exception.FoodNotFoundException;
import com.example.Swiggato.Exception.RestaurantNotFoundException;
import com.example.Swiggato.Model.FoodItem;
import com.example.Swiggato.Model.Restaurant;
import com.example.Swiggato.Repository.FoodItemRepository;
import com.example.Swiggato.Repository.RestaurantRepository;
import com.example.Swiggato.Transformers.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.Swiggato.Enum.FoodCategory.MAIN_COURSE;

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

    public List<FoodItemResponse> allFodOfCategory(FoodCategory foodCategory) {

        List<FoodItem> foodItems = foodItemRepository.findByfoodCategory(foodCategory);

        List<FoodItemResponse> foodItemResponses = foodItems.stream()
                .map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return foodItemResponses;
    }

    public List<FoodItemResponse> mainCourse(int restaurantId, int price) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();

        List<FoodItemResponse> foodItemResponses = restaurant.getFoodItemList()
                .stream().filter(foodItem -> foodItem.getFoodCategory()==MAIN_COURSE && foodItem.getCost()>price)
                .map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        if(foodItemResponses.isEmpty()){
            throw new FoodNotFoundException("No food available right now :(");
        }
        return foodItemResponses;

    }
}
