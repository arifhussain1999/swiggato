package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.response.MenuResponse;
import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Exception.FoodNotFoundException;
import com.example.Swiggato.Exception.RestaurantNotFoundException;
import com.example.Swiggato.Model.MenuItem;
import com.example.Swiggato.Model.Restaurant;
import com.example.Swiggato.Repository.FoodItemRepository;
import com.example.Swiggato.Repository.RestaurantRepository;
import com.example.Swiggato.Transformers.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<MenuResponse> vegFoods(int restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();

        List<MenuResponse> vegFoodResponses = restaurant.getMenuItemList()
                .stream().filter(foodItem -> foodItem.isVeg())
                .map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return vegFoodResponses;
    }

    public List<MenuResponse> allFodOfCategory(FoodCategory foodCategory) {

        List<MenuItem> menuItems = foodItemRepository.findByfoodCategory(foodCategory);

        List<MenuResponse> menuRespons = menuItems.stream()
                .map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return menuRespons;
    }

    public List<MenuResponse> mainCourse(int restaurantId, int price) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();

        List<MenuResponse> menuRespons = restaurant.getMenuItemList()
                .stream().filter(foodItem -> foodItem.getFoodCategory()==MAIN_COURSE && foodItem.getCost()>price)
                .map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        if(menuRespons.isEmpty()){
            throw new FoodNotFoundException("No food available right now :(");
        }
        return menuRespons;

    }
}
