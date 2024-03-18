package com.example.Swiggato.DTO.request;

import com.example.Swiggato.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    String name;

    String email;

    String address;


    String mobile;


    Gender gender;
}
