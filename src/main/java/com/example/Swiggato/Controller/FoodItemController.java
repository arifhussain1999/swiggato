package com.example.Swiggato.Controller;

import com.example.Swiggato.DTO.request.FoodItemRequest;
import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodItem")
public class FoodItemController {

    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/add-foodItem")
    public ResponseEntity addFoodItem(@RequestBody FoodItemRequest foodItemRequest){
        FoodItemResponse foodItemResponse = foodItemService.addFoodItem(foodItemRequest);
        return new ResponseEntity<>(foodItemResponse, HttpStatus.CREATED);
    }
}
