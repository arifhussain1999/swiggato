package com.example.Swiggato.Service;

import com.example.Swiggato.DTO.request.CustomerRequest;
import com.example.Swiggato.DTO.response.CustomerResponse;
import com.example.Swiggato.Exception.CustomerNotFoundException;
import com.example.Swiggato.Model.Cart;
import com.example.Swiggato.Model.Customer;
import com.example.Swiggato.Repository.CustomerRepository;
import com.example.Swiggato.Transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse addCustomer(CustomerRequest customerReq) {

        //first convert  dto -> customer model

        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerReq);

        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        // set the cart in customer
        customer.setCart(cart);

       Customer savedCustomer = customerRepository.save(customer);  //saves both customer and cart

        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);

    }

    public CustomerResponse getCustomer(String mobile) {
        Customer customer = customerRepository.findByMobile(mobile);
        if (customer==null) {
            throw new CustomerNotFoundException("Invalid mobile number!!!");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}
