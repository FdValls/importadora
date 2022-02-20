package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdvalls.importadora.dto.SocialNetworkDTO;
import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.model.Importer;
import com.fdvalls.importadora.model.SocialNetwork;
import com.fdvalls.importadora.repository.NetworkRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SocialNetworkServiceTest {

    @Mock
    private NetworkRepository networkRepository;
    private SocialNetworkService networkService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        when(networkRepository.findNetworkById(eq(1L)))
            .thenReturn(SocialNetwork.builder()
                .id(1L)
                .description("Instagram")
                .url("https://instagram.com")
                .dealer(new Dealer(22L, "Storm", "88-41548588-7", "Alvio 1544", "116662587", null, null, null))
                .importer(Importer.builder()
                    .id(1l)
                    .razonSocial("Pollo Hnos")
                    .cuil("63-958789586-8")
                    .address("Old bridge 455")
                    .telephone("1164859786")
                    .country("Irland")
                    .networks(null)
                    .motorcycles(null)
                    .dealers(null)
                    .build()
                    )
                    .build());
                
        this.networkService = new SocialNetworkService(networkRepository);
    }

    @Test
    void test_findeNetworkById() {
        SocialNetworkDTO network = this.networkService.findNetworkById(1L);

        assertNotNull(network);
        assertEquals("Instagram", network.getDescription());
        assertEquals("https://instagram.com", network.getUrl());
    }

    @Test
    void test_saveNetwork() {
        SocialNetworkDTO dto = new SocialNetworkDTO(2L, "Facebook", "https://facebook.com", null, null);
        this.networkService.saveNetwork(dto);

        verify(networkRepository, times(1)).save(any());
    }


    
}
