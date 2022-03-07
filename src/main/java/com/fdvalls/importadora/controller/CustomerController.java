package com.fdvalls.importadora.controller;

import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public CustomerDTO createCustomer(HttpServletRequest request, @RequestBody CustomerDTO customerDTO)
            throws Exception {
        return this.customerService.saveCustomer(customerDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(HttpServletRequest request) {
        return ResponseEntity.ok().body(this.customerService.findAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(HttpServletRequest request,
            @PathVariable("id") Long id,
            @RequestBody CustomerDTO dto) throws Exception {
        return ResponseEntity.ok().body(this.customerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request,
            @PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(this.customerService.delete(id));
    }

}
