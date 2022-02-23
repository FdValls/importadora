package com.fdvalls.importadora.controller;

import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(HttpServletRequest request, @RequestBody CustomerDTO customerDTO) {
        if(this.customerService.saveCustomer(customerDTO) != null){
            return new ResponseEntity<>("Resultado OK", HttpStatus.OK);
        }
       
        return new ResponseEntity<>("Resultado NOK", HttpStatus.NOT_FOUND);


    }


    
}
