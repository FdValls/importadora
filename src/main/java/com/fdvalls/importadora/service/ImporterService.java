package com.fdvalls.importadora.service;

import java.util.List;

import com.fdvalls.importadora.dto.ImporterDTO;
import com.fdvalls.importadora.dto.SocialNetworkDTO;
import com.fdvalls.importadora.model.Importer;
import com.fdvalls.importadora.model.SocialNetwork;
import com.fdvalls.importadora.repository.ImporterRepository;

import org.springframework.stereotype.Service;

@Service
public class ImporterService {

    private final ImporterRepository importerRepository;

    public ImporterService(ImporterRepository importerRepository) {
        this.importerRepository = importerRepository;
    }

    public ImporterDTO findeImporterById(Long id) {
        Importer importer = this.importerRepository.findImporterById(id);

        return this.transformModelToDTO(importer);
    }

    private ImporterDTO transformModelToDTO(Importer i) {
        List<SocialNetworkDTO> networks = i.getNetworks().stream()
                .map(snts -> SocialNetworkDTO.builder()
                        .description(snts.getDescription())
                        .url(snts.getUrl())
                        .build())
                .toList();

        return ImporterDTO.builder()
                .id(i.getId())
                .razonSocial(i.getRazonSocial())
                .cuil(i.getCuil())
                .address(i.getAddress())
                .telephone(i.getTelephone())
                .country(i.getCountry())
                .networks(networks)
                .build();
    }

    public ImporterDTO saveImporter(ImporterDTO dto) throws Exception {
        if (this.importerRepository.findImporterByCuil(dto.getCuil()) != null) {
            throw new Exception("cuil already exist");
        }

        List<SocialNetwork> networks = dto.getNetworks().stream()
                .map(snts -> SocialNetwork.builder()
                        .description(snts.getDescription())
                        .url(snts.getUrl())
                        .build())
                .toList();

        Importer importer = Importer.builder()
                .id(dto.getId())
                .razonSocial(dto.getRazonSocial())
                .cuil(dto.getCuil())
                .address(dto.getAddress())
                .telephone(dto.getTelephone())
                .country(dto.getCountry())
                .networks(networks)
                .build();

        importer = this.importerRepository.save(importer);

        return this.transformModelToDTO(importer);
    }

    public List<ImporterDTO> getAllImporters() {
        return this.importerRepository.findAll().stream().map(this::transformModelToDTO).toList();

    }

}
