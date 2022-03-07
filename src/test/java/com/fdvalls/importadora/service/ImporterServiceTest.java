package com.fdvalls.importadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdvalls.importadora.dto.ImporterDTO;
import com.fdvalls.importadora.model.Importer;
import com.fdvalls.importadora.repository.ImporterRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ImporterServiceTest {

    @Mock
    private ImporterRepository importerRepository;
    private ImporterService importerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(importerRepository.findImporterById(eq(1L)))
                .thenReturn(Importer.builder()
                        .id(1l)
                        .razonSocial("Spring-Motors")
                        .cuil("31-254698965-7")
                        .address("Av.Long 4688")
                        .telephone("1164978897")
                        .country("Canada")
                        .networks(null)
                        .motorcycles(null)
                        .dealers(null)
                        .build());

        this.importerService = new ImporterService(importerRepository);
    }

    @Test
    void test_findeImporterById() {
        ImporterDTO importer = this.importerService.findeImporterById(1l);

        assertNotNull(importer);
        assertEquals("Spring-Motors", importer.getRazonSocial());
        assertEquals("31-254698965-7", importer.getCuil());
        assertEquals("Av.Long 4688", importer.getAddress());
        assertEquals("1164978897", importer.getTelephone());
        // assertNull(importer.getNetworks());
        // assertNull(importer.getMotorcycles());
        // assertNull(importer.getDealers());
    }

    @Test
    void test_saveImporter() throws Exception {
        ImporterDTO dto = new ImporterDTO(2l, "Spring-Motors", "31-254698965-7", "Av.Long 4688","1164978897", "Canada",null,null);
        this.importerService.saveImporter(dto);

        verify(importerRepository, times(1)).save(any());
    }
    
}
