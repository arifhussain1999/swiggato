package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.request.RestaurantRequest;
import com.example.Swiggato.DTO.response.RestaurantResponse;
import com.example.Swiggato.Exception.RestaurantNotFoundException;
import com.example.Swiggato.Model.Restaurant;
import com.example.Swiggato.Repository.RestaurantRepository;
import com.example.Swiggato.Transformers.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
