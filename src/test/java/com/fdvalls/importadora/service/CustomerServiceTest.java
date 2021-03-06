package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.repository.CustomerRepository;
import com.fdvalls.importadora.repository.DealerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DealerRepository dealerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(customerRepository.findCustomerById(eq(1L)))
                .thenReturn(Customer.builder()
                        .id(1L)
                        .name("Fernando")
                        .lastname("Valls")
                        .age(31)
                        .identification("35323873")
                        .build());

        when(customerRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(dealerRepository.findById(anyLong())).thenReturn(Optional.of(Dealer.builder()
            .customers(new ArrayList<>())
            .build()));
        this.customerService = new CustomerService(customerRepository, dealerRepository);
    }

    @Test
    void test_findCustomerById() {
        CustomerDTO customer = this.customerService.findCustomerById(1L);
        assertNotNull(customer);
        assertEquals("35323873", customer.getIdentification());
        assertEquals("Fernando", customer.getName());
        assertEquals("Valls", customer.getLastname());
        assertEquals(31, customer.getAge());
        assertEquals("35323873", customer.getIdentification());
        // hacerlo con todos los atributos ----->ok
    }

    @Test
    void test_saveCustomer() throws Exception {
        CustomerDTO dto = CustomerDTO.builder()
            .id(2L)
            .name("Alejandro")
            .lastname("Valls")
            .age(39)
            .identification("30307509")
            .dealerId(1L)
            .build();

        this.customerService.saveCustomer(dto);

        verify(customerRepository, times(1)).findByIdentification("30307509");
        verify(customerRepository, times(1)).save(any());
    }

    @Test
    void test_saveExistingCustomer()  {
        when(customerRepository.findByIdentification("30307509")).thenReturn(Customer.builder()
                .id(1L)
                .name("Alejandro")
                .lastname("Valls")
                .age(31)
                .identification("30307509")
                .build());

        assertThrows(Exception.class, () -> {
            CustomerDTO dto = CustomerDTO.builder()
                    .id(2L)
                    .name("Alejandro")
                    .lastname("Valls")
                    .age(39)
                    .identification("30307509")
                    .build();
            this.customerService.saveCustomer(dto);
        });
    }

}
