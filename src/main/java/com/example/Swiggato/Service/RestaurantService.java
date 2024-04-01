package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.request.MenuRequest;
import com.example.Swiggato.DTO.request.RestaurantRequest;
import com.example.Swiggato.DTO.response.MenuResponse;
import com.example.Swiggato.DTO.response.RestaurantResponse;
import com.example.Swiggato.Exception.RestaurantNotFoundException;
import com.example.Swiggato.Model.MenuItem;
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

    public RestaurantResponse addMenuItemToRestaurant(MenuRequest menuRequest) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(menuRequest.getRestaurantId());

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();
        MenuItem menuItem = FoodTransformer.FoodItemRequestToFood(menuRequest);

        menuItem.setRestaurant(restaurant);
        restaurant.getMenuItemList().add(menuItem);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    public List<MenuResponse> getMenu(int restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found!");
        }
        Restaurant restaurant = restaurantOptional.get();

        List<MenuResponse> menuRespons = restaurant.getMenuItemList()
                .stream().map(foodItem -> FoodTransformer.FoodToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return menuRespons;

    }
}
