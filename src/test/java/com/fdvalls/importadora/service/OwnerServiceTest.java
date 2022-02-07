package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdvalls.importadora.dto.OwnerDTO;
import com.fdvalls.importadora.model.Owner;
import com.fdvalls.importadora.repository.OwnerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OwnerServiceTest {
    
    @Mock
    private OwnerRepository ownerRepository;
    private OwnerService ownerService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        when(ownerRepository.findOwnerById(eq(1L)))
                .thenReturn(Owner.builder()
                    .id(1L)           
                    .name("Fernando")
                    .lastname("Valls")
                    .old(31)
                    .identification("35323873")
                    .build());
        
        this.ownerService = new OwnerService(ownerRepository);
    } 

    @Test
    void test_findOwnerByIdentification(){
        OwnerDTO owner = this.ownerService.findOwnerById(1L);

        assertNotNull(owner);
        assertEquals("35323873", owner.getIdentification());
    }

    @Test
    void test_saveOwner(){
        OwnerDTO dto = new OwnerDTO(2L,"Alejandro", "Valls", 39, "30307509");
        this.ownerService.saveOwner(dto);

        verify(ownerRepository, times(1)).save(any());
    }



}
