package com.fdvalls.importadora.service;

import java.util.List;
import java.util.Optional;

import com.fdvalls.importadora.dto.DealerDTO;
import com.fdvalls.importadora.dto.SocialNetworkDTO;
import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.model.Importer;
import com.fdvalls.importadora.model.SocialNetwork;
import com.fdvalls.importadora.repository.DealerRepository;
import com.fdvalls.importadora.exception.DealerNotFoundException;
import com.fdvalls.importadora.exception.NotExist;
import com.fdvalls.importadora.repository.ImporterRepository;

import org.springframework.stereotype.Service;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;
    private final ImporterRepository importerRepository;

    public DealerService(DealerRepository dealerRepository, ImporterRepository importerRepository) {
        this.dealerRepository = dealerRepository;
        this.importerRepository = importerRepository;
    }

    public DealerDTO findDealerById(Long id) {
        Dealer dealer = this.dealerRepository.findDealerById(id);

        return this.transformModelToDTO(dealer);
    }

    private DealerDTO transformModelToDTO(Dealer d) {
        List<SocialNetworkDTO> networks = d.getNetworks().stream()
                .map(snts -> SocialNetworkDTO.builder()
                        .description(snts.getDescription())
                        .url(snts.getUrl())
                        .build())
                .toList();

        return DealerDTO.builder()
                .id(d.getId())
                .razonSocial(d.getRazonSocial())
                .cuil(d.getCuil())
                .address(d.getAddress())
                .telephone(d.getTelephone())
                .socialNetworks(networks)
                .build();
    }

    public DealerDTO saveDealer(DealerDTO dto) throws Exception {
        if (this.dealerRepository.findDealerByCuil(dto.getCuil()) != null) {
            throw new Exception("cuil already exist");
        }

        List<SocialNetwork> networks = dto.getSocialNetworks().stream()
                .map(snts -> SocialNetwork.builder()
                        .description(snts.getDescription())
                        .url(snts.getUrl())
                        .build())
                .toList();

        Optional<Importer> importerOptional = this.importerRepository.findById(dto.getImporterId());
        Importer importer = importerOptional.orElseThrow(
                () -> new DealerNotFoundException("Importer with id " + dto.getImporterId() + " does not exists"));

        Dealer dealer = Dealer.builder()
                .id(dto.getId())
                .razonSocial(dto.getRazonSocial())
                .cuil(dto.getCuil())
                .address(dto.getAddress())
                .telephone(dto.getTelephone())
                .networks(networks)
                .build();

        dealer = this.dealerRepository.save(dealer);
        importer.getDealers().add(dealer);
        this.importerRepository.save(importer);

        return this.transformModelToDTO(dealer);
    }

    public DealerDTO delete(Long id) throws Exception {
        Dealer dealerDetele = this.dealerRepository.findDealerById(id);
        if (dealerDetele == null) {
            throw new NotExist("id not exist");
        }
        this.dealerRepository.delete(dealerDetele);
        return this.transformModelToDTO(dealerDetele);

    }

    public List<DealerDTO> findAllDealers() {
        return this.dealerRepository.findAll().stream().map(this::transformModelToDTO).toList();
    }

    public DealerDTO update(Long id, DealerDTO dto) {
        Dealer dealerUpdate = this.dealerRepository.findDealerById(id);
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        } else if (dealerUpdate == null) {
            throw new IllegalArgumentException("customer not exist");
        } else {
            return this.transformModelToDTO(this.dealerRepository.save(Dealer.builder()
                    .id(dealerUpdate.getId())
                    .razonSocial(dto.getRazonSocial())
                    .cuil(dto.getCuil())
                    .address(dto.getAddress())
                    .telephone(dto.getTelephone())
                    .build()));
        }
    }

}
