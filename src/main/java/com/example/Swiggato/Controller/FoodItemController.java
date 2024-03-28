package com.example.Swiggato.Controller;

import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.Model.FoodItem;
import com.example.Swiggato.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    FoodItemService foodItemService;

 @GetMapping("/get/food/{id}")
    public ResponseEntity vegFoods(@PathVariable("id") int restaurantId){
     try {
         List<FoodItemResponse> foodItemList = foodItemService.vegFoods(restaurantId);
         return new ResponseEntity<>(foodItemList, HttpStatus.FOUND);
     }
  catch (Exception e){
         return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
  }

 }
}
