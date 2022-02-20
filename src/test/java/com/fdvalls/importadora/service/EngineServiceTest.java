package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdvalls.importadora.dto.EngineDTO;
import com.fdvalls.importadora.model.Engine;
import com.fdvalls.importadora.repository.EngineRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EngineServiceTest {


    @Mock
    private EngineRepository engineRepository;
    private EngineService engineService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(engineRepository.findEngineById(eq(1L)))
                .thenReturn(Engine.builder()
                        .id(1l)
                        .brand("Ducati")
                        .serialNumber("151455EQPL98")
                        .horsePower("500cc")
                        .build());

        this.engineService = new EngineService(engineRepository);
    }

    @Test
    void test_findEngineById() {
        EngineDTO engine = this.engineService.fEngineById(1L);

        assertNotNull(engine);
        assertEquals("Ducati", engine.getBrand());
        assertEquals("151455EQPL98", engine.getSerialNumber());
        assertEquals("500cc", engine.getHorsePower());
    }

    @Test
    void test_saveDealer() {
        EngineDTO dto = new EngineDTO(2l, "Benelli", "1334256TYU87W", "400cc");
        this.engineService.saveEngine(dto);

        verify(engineRepository, times(1)).save(any());
    }

    @Test
    void test_saveMotorcycleDomainAlreadyExists() {
        assertThrows(IllegalArgumentException.class, () -> {
            EngineDTO dto = new EngineDTO(1l, "Ducati", "151455EQPL98", "500cc");
            this.engineService.saveEngine(dto);
        });
    }
    



}
