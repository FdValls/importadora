package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fdvalls.importadora.dto.DealerDTO;
import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.repository.DealerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DealerServiceTest {

    @Mock
    private DealerRepository dealerRepository;
    private DealerService dealerService;
    private List<Customer> customers;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.customers = new ArrayList<>();
        this.customers.add(new Customer(2l, "Marcelo", "Valls", 29, "37171405"));
        when(dealerRepository.findDealerById(eq(1L)))
                .thenReturn(Dealer.builder()
                        .id(1l)
                        .razonSocial("Motors-Valls")
                        .cuil("20-35323873-7")
                        .address("Mariano Acha 1066")
                        .telephone("1123029425")
                        .networks(null)
                        .motorcycles(null)
                        .customers(customers)
                        .build());

        this.dealerService = new DealerService(dealerRepository);
    }

    @Test
    void test_findeDealerById() {
        DealerDTO dealer = this.dealerService.findDealerById(1L);

        assertNotNull(dealer);
        assertEquals("Motors-Valls", dealer.getRazonSocial());
        assertEquals("20-35323873-7", dealer.getCuil());
        assertEquals("Mariano Acha 1066", dealer.getAddress());
        assertEquals("1123029425", dealer.getTelephone());
        assertNull(dealer.getNetworks());
        assertNull(dealer.getMotorcycles());
        assertNotNull(dealer.getCustomers());
    }

    @Test
    void test_saveDealer() {
        DealerDTO dto = new DealerDTO(2l, "Hell-Motors", "24-12081016-7", "Avalos 1277", "1123029425", null, null, this.customers);
        this.dealerService.saveDealer(dto);

        verify(dealerRepository, times(1)).save(any());
    }

    @Test
    void test_saveDealerIdAlreadyExists() {
        assertThrows(IllegalArgumentException.class, () -> {
            DealerDTO dto = new DealerDTO(1l, "Motors-Valls", "20-35323873-7", "Mariano Acha 1066", "1123029425", null, null, this.customers);
            this.dealerService.saveDealer(dto);
        });
    }
    

    
}
