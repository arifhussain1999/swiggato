package com.example.Swiggato.Controller;

import com.example.Swiggato.DTO.request.FoodRequest;
import com.example.Swiggato.DTO.request.RestaurantRequest;
import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.DTO.response.RestaurantResponse;
import com.example.Swiggato.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {


    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/add-restaurant")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse response = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
     @PutMapping("/update/status")
    public ResponseEntity updateStatus(@RequestParam int id){
        try{
            String status = restaurantService.updateStatus(id);
            return new ResponseEntity<>(status,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
     }

     @PostMapping("/add/food")
     public ResponseEntity addFoodToRestaurant(@RequestBody FoodRequest foodRequest){
        try{
            RestaurantResponse restaurantResponse = restaurantService.addFoodToRestaurant(foodRequest);
            return new ResponseEntity<>(restaurantResponse,HttpStatus.CREATED);
        }
       catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
     }

     @GetMapping("/get/id/{id}")
    public ResponseEntity getMenu(@PathVariable("id") int restaurantId){
        try{
            List<FoodItemResponse> foodItems = restaurantService.getMenu(restaurantId);
           return new ResponseEntity<>(foodItems,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
     }

}
