package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.ImporterDTO;
import com.fdvalls.importadora.model.Importer;
import com.fdvalls.importadora.repository.ImporterRepository;

import org.springframework.stereotype.Service;

@Service
public class ImporterService {

    private ImporterRepository importerRepository;

    public ImporterService (ImporterRepository importerRepository){
        this.importerRepository = importerRepository;
    }

    public ImporterDTO findeImporterById(Long id) {
        Importer importer = this.importerRepository.findImporterById(id);

        return this.transformModelToDTO(importer);
    }

    private ImporterDTO transformModelToDTO(Importer i) {
        return new ImporterDTO(i.getId(), i.getRazonSocial(),
                              i.getCuil(), i.getAddress(), i.getTelephone(),
                              i.getCountry());
    }

    public ImporterDTO saveImporter(ImporterDTO dto) {
        if (this.importerRepository.findImporterById(dto.getId()) != null) {
            throw new IllegalArgumentException();
        }
        return this.transformModelToDTO(this.importerRepository.save(Importer.builder()
                .id(dto.getId())
                .razonSocial(dto.getRazonSocial())
                .cuil(dto.getCuil())
                .address(dto.getAddress())
                .telephone(dto.getTelephone())
                .country(dto.getCountry())
                // .networks(dto.getNetworks())
                // .motorcycles(dto.getMotorcycles())
                // .dealers(dto.getDealers())
                .build()));
    }
    
}
