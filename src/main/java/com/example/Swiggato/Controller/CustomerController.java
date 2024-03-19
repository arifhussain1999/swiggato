package com.example.Swiggato.Controller;

import com.example.Swiggato.DTO.request.CustomerRequest;
import com.example.Swiggato.DTO.response.CustomerResponse;
import com.example.Swiggato.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


   //  final CustomerService /customerService;
//     @Autowired
//     public CustomerController(CustomerService customerService){
//         this.customerService=customerService;
//     }

    @PostMapping("/add-customer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerReq){
              CustomerResponse customerResponse= customerService.addCustomer(customerReq);
              return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/mobile/{mobile}")
    public ResponseEntity getCustomer(@PathVariable("mobile") String mobile){

        try {
            CustomerResponse customerResponse = customerService.getCustomer(mobile);
            return new ResponseEntity(customerResponse,HttpStatus.CREATED);
        }
       catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }
}
