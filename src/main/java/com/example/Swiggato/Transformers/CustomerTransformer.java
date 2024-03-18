package com.example.Swiggato.Transformers;

import com.example.Swiggato.DTO.request.CustomerRequest;
import com.example.Swiggato.DTO.response.CartResponse;
import com.example.Swiggato.DTO.response.CustomerResponse;
import com.example.Swiggato.Model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestToCustomer(CustomerRequest customerReqDTO){

        return Customer.builder()
                .name(customerReqDTO.getName())
                .address(customerReqDTO.getAddress())
                .email(customerReqDTO.getEmail())
                .mobile(customerReqDTO.getMobile())
                .gender(customerReqDTO.getGender())
                .build();

    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer){

        CartResponse cartResponse = CartTransformer.CartToCartResponse(customer.getCart());

        return CustomerResponse.builder()
                .name(customer.getName())
                .mobile(customer.getMobile())
                .address(customer.getAddress())
                .cart(cartResponse)
                .build();
    }
}
