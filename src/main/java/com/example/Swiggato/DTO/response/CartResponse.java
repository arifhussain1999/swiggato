package com.example.Swiggato.DTO.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    double cartTotal;

    List<FoodResponse> foodResponseList;
}
