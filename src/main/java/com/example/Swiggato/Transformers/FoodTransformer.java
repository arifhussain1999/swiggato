package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.request.MenuRequest;
import com.example.Swiggato.DTO.response.MenuResponse;
import com.example.Swiggato.Model.MenuItem;

public class FoodTransformer {

    public static MenuResponse FoodToFoodResponse(MenuItem menuItem) {
//        if (menuItems==null){
//           return new ArrayList<>();
//        }
//        List<MenuResponse> foodResponseList = new ArrayList<>();
//        for (MenuItem menuItem : menuItems) {
//            MenuResponse /foodResponse = MenuResponse.builder()
//                    .dishName(menuItem.getDishName())
//                    .cost(menuItem.getCost())
//                    .build();
//
//            foodResponseList.add(foodResponse);
//        }
//        return foodResponseList;

        return MenuResponse.builder()
                .dishName(menuItem.getDishName())
                .cost(menuItem.getCost())
                .foodCategory(menuItem.getFoodCategory())
                .veg(menuItem.isVeg())
                .build();
    }

    public static MenuItem FoodItemRequestToFood(MenuRequest menuRequest){
        return MenuItem.builder()
                .dishName(menuRequest.getDishName())
                .foodCategory(menuRequest.getFoodCategory())
                .available(menuRequest.isAvailable())
                .veg(menuRequest.isVeg())
                .cost(menuRequest.getCost())
                .build();
    }
}
