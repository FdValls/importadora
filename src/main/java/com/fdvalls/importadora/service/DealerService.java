package com.fdvalls.importadora.service;

import java.util.List;

import com.fdvalls.importadora.dto.DealerDTO;
import com.fdvalls.importadora.dto.SocialNetworkDTO;
import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.model.SocialNetwork;
import com.fdvalls.importadora.repository.DealerRepository;
import com.fdvalls.importadora.repository.SocialNetworkRepository;

import org.springframework.stereotype.Service;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;
    private final SocialNetworkService socialNetworkService;
    private final SocialNetworkRepository socialNetworkRepository;

    public DealerService(DealerRepository dealerRepository, SocialNetworkService socialNetworkService,
            SocialNetworkRepository socialNetworkRepository) {
        this.dealerRepository = dealerRepository;
        this.socialNetworkService = socialNetworkService;
        this.socialNetworkRepository = socialNetworkRepository;
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

        // List<SocialNetwork> networks = new ArrayList<>();

        // for (SocialNetworkDTO snts : dto.getSocialNetworks()) {
        //     SocialNetwork socialNetwork = SocialNetwork.builder()
        //         .description(snts.getDescription())
        //         .url(snts.getUrl())
        //         .build();
            
        //     networks.add(socialNetwork);
        // }

        List<SocialNetwork> networks = dto.getSocialNetworks().stream()
            .map(snts -> SocialNetwork.builder()
                    .description(snts.getDescription())
                    .url(snts.getUrl())
                    .build())
            .toList();

        return this.transformModelToDTO(this.dealerRepository.save(Dealer.builder()
                .id(dto.getId())
                .razonSocial(dto.getRazonSocial())
                .cuil(dto.getCuil())
                .address(dto.getAddress())
                .telephone(dto.getTelephone())
                .networks(networks)
                .build()));
    }

    public DealerDTO delete(Long id) throws Exception {
        Dealer dealerDetele = this.dealerRepository.findDealerById(id);
        if (dealerDetele == null) {
            throw new Exception("id not exist");
        }
        this.dealerRepository.delete(dealerDetele);
        return this.transformModelToDTO(dealerDetele);

    }

    public List<DealerDTO> findAllDealers() throws Exception {
        if (this.dealerRepository.findAll().isEmpty()) {
            throw new Exception("List null");
        }
        return this.dealerRepository.findAll().stream().map(this::transformModelToDTO).toList();
    }

    public DealerDTO update(Long id, DealerDTO dto) {
        Dealer dealerUpdate = this.dealerRepository.findDealerById(id);
        // List<SocialNetwork> networks = this.socialNetworkRepository.findAll();
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
                    // .networks(dto.getNetworks())
                    // .motorcycles(dto.getMotorcycles())
                    // .customers(dto.getCustomers())
                    .build()));
        }
    }

}
