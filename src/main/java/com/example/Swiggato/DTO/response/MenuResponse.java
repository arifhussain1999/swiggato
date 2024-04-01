package com.example.Swiggato.DTO.response;

import com.example.Swiggato.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {

    String dishName;

    FoodCategory foodCategory;

    double cost;

    boolean veg;
}
