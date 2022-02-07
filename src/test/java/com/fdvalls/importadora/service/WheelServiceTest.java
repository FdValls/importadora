package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdvalls.importadora.dto.WheelDTO;
import com.fdvalls.importadora.model.Wheel;
import com.fdvalls.importadora.repository.WheelRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WheelServiceTest {

    @Mock
    private WheelRepository wheelRepository;
    private WheelService wheelService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(wheelRepository.findWheelById(eq(1L)))
                .thenReturn(Wheel.builder()
                        .id(1L)
                        .marca("Michelin Primacy 4 103Y")
                        .rodado(17)
                        .build());

        this.wheelService = new WheelService(wheelRepository);
    }

    @Test
    void test_findOwnerByIdentification(){
        WheelDTO wheel = this.wheelService.findWheelById(1L);

        assertNotNull(wheel);
    }

    @Test
    void test_saveOwner(){
        WheelDTO dto = new WheelDTO(2L, "Pirelli Super City", 17);
        this.wheelService.saveWheel(dto);

        verify(wheelRepository, times(1)).save(any());
    }
}
