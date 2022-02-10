package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTest {
    
    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        when(customerRepository.findCustomerById(eq(1L)))
                .thenReturn(Customer.builder()
                    .id(1L)           
                    .name("Fernando")
                    .lastname("Valls")
                    .old(31)
                    .identification("35323873")
                    .build());
        
        this.customerService = new CustomerService(customerRepository);
    } 

    @Test
    void test_findOwnerByIdentification(){
        CustomerDTO owner = this.customerService.findCustomerById(1L);

        assertNotNull(owner);
        assertEquals("35323873", owner.getIdentification());
    }

    @Test
    void test_saveOwner(){
        CustomerDTO dto = new CustomerDTO(2L,"Alejandro", "Valls", 39, "30307509");
        this.customerService.saveCustomer(dto);

        verify(customerRepository, times(1)).save(any());
    }



}
