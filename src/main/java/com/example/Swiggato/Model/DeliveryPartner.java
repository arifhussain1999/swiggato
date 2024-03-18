package com.example.Swiggato.Model;

import com.example.Swiggato.Enum.Gender;
import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data // getter,setter,toString,required args contructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_partner")
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 4,max = 27,message = "name size too small/large")
    String name;

    @Column(unique = true,nullable = false)
    @Size(min = 10,max = 10,message = "invalid mobile no.!")
    String mobile;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "deliveryPartner",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntityList = new ArrayList<>();
}
