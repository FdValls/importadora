package com.fdvalls.importadora.controller;


import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(HttpServletRequest request, @RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.ok().body(this.customerService.saveCustomer(customerDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @GetMapping()
	public ResponseEntity<?> getAllCareers() {
		try {
            return ResponseEntity.ok().body(this.customerService.findAllCustomers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

    
    @PutMapping("/{id}")
	public void updateCustomer(HttpServletRequest request,
			@PathVariable("id") Long id,
			@RequestBody CustomerDTO dto) {
		try {
            this.customerService.update(id, dto.getName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
    
    

}
