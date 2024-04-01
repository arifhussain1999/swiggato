package com.example.Swiggato.Model;

import com.example.Swiggato.Enum.RestaurantCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data // getter,setter,toString,required args constructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String location;

    @Column(unique = true,nullable = false)
    @Size(min = 10,max = 10)
    String contactNo;

    boolean open;

    @Enumerated(EnumType.STRING)
    RestaurantCategory restaurantCategory;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<MenuItem> menuItemList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntities = new ArrayList<>();

}
