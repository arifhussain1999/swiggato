package com.example.Swiggato.DTO.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    String name;

    String address;

    String mobile;

    CartResponse cart;
}
