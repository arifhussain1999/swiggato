package com.example.Swiggato.DTO.request;
import com.example.Swiggato.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {

    int restaurantId;

    String dishName;

    FoodCategory foodCategory;

    double cost;

    boolean veg;

    boolean available;
}
