package com.example.Swiggato.DTO.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data // getter,setter,toString,required args constructor
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {
    String name;

    boolean open;

    String contactNo;

    List<MenuResponse> menu;

    String location;
}
