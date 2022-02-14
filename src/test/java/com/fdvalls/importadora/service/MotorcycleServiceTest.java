package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdvalls.importadora.dto.MotorcycleDTO;
import com.fdvalls.importadora.model.Engine;
import com.fdvalls.importadora.model.Motorcycle;
import com.fdvalls.importadora.repository.MotorcycleRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MotorcycleServiceTest {

    @Mock
    private MotorcycleRepository motorcycleRepository;
    private MotorcycleService motorcycleService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(motorcycleRepository.findByDomain(eq("A123ABC")))
                .thenReturn(Motorcycle.builder()
                        .id(1l)
                        .brand("Honda")
                        .engine(
                                Engine.builder()
                                        .brand("Honda")
                                        .horsePower("600cc")
                                        .build())
                        .domain("A123ABC")
                        .build());

        this.motorcycleService = new MotorcycleService(motorcycleRepository);
    }

    @Test
    void test_findMotorcycleByDomain() {
        MotorcycleDTO motorcycle = this.motorcycleService.findMotorcycleByDomain("A123ABC");

        assertNotNull(motorcycle);
        assertEquals("Honda", motorcycle.getBrand());
        assertEquals("Honda", motorcycle.getEngineBrand());
        assertEquals("600cc", motorcycle.getEnginePower());
        assertEquals("A123ABC", motorcycle.getDomain());
    }

    @Test
    void test_saveMotorcycle() {
        MotorcycleDTO dto = new MotorcycleDTO(1L, "Zanella", "Keeway", "200cc", "A111CCC");
        this.motorcycleService.saveMotorcycle(dto);

        verify(motorcycleRepository, times(1)).save(any());
    }

    @Test
    void test_saveMotorcycleDomainAlreadyExists() {
        // A123ABC ya existe
        // when(motorcycleRepository.findMotorcycleByDomain(eq("A111AAA"))).thenReturn(value)
        assertThrows(IllegalArgumentException.class, () -> {
            MotorcycleDTO dto = new MotorcycleDTO(1L, "Zanella", "Keeway", "200cc", "A123ABC");
            this.motorcycleService.saveMotorcycle(dto);
        });
    }

    @Test
    void test_saveMotorcycleException() {
        MotorcycleDTO dto = new MotorcycleDTO(2L, "Zanella", "Keeway", "200cc", "A111CCC");
        this.motorcycleService.saveMotorcycle(dto);

        verify(motorcycleRepository, times(1)).save(any());
    }

}
