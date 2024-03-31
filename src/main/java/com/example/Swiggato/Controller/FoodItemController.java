package com.example.Swiggato.Controller;

import com.example.Swiggato.DTO.response.FoodItemResponse;
import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Model.FoodItem;
import com.example.Swiggato.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    FoodItemService foodItemService;

 @GetMapping("/get/veg-food/{id}")
    public ResponseEntity vegFoods(@PathVariable("id") int restaurantId){
     try {
         List<FoodItemResponse> foodItemList = foodItemService.vegFoods(restaurantId);
         return new ResponseEntity<>(foodItemList, HttpStatus.FOUND);
     }
  catch (Exception e){
         return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
  }
 }

    @GetMapping("/get/all-food/{category}")
    public ResponseEntity allFoodOfCategory(@PathVariable("category") FoodCategory foodCategory) {
        List<FoodItemResponse> foodItemResponses = foodItemService.allFodOfCategory(foodCategory);
        return new ResponseEntity<>(foodItemResponses,HttpStatus.FOUND);
    }

    @GetMapping("/get/MAIN_COURSE/{id}")
    public ResponseEntity mainCourse(@PathVariable("id") int restaurantId,@RequestParam int price){
     try {
         List<FoodItemResponse> foodResponse=foodItemService.mainCourse(restaurantId,price);
         return new ResponseEntity<>(foodResponse,HttpStatus.FOUND);
     }
     catch (Exception e){
         return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
     }
    }
}
