package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.request.RestaurantRequest;
import com.example.Swiggato.DTO.response.RestaurantResponse;
import com.example.Swiggato.Model.Restaurant;
import com.example.Swiggato.Repository.RestaurantRepository;
import com.example.Swiggato.Transformers.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        //    requestDTO -> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // model to responseDTO

        RestaurantResponse response = RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
        return response;
    }
}
