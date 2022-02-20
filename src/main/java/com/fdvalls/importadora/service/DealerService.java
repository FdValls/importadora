package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.DealerDTO;
import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.repository.DealerRepository;

public class DealerService {

    private DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public DealerDTO findDealerById(Long id) {
        Dealer dealer = this.dealerRepository.findDealerById(id);

        return this.transformModelToDTO(dealer);
    }

    private DealerDTO transformModelToDTO(Dealer d) {
        return new DealerDTO(d.getId(),
                             d.getRazonSocial(),
                             d.getCuil(),
                             d.getAddress(),
                             d.getTelephone(),
                             d.getNetworks(),
                             d.getMotorcycles(), 
                             d.getCustomers()

        );
    }

    public void saveDealer(DealerDTO dto) {
        if (this.dealerRepository.findDealerById(dto.getId()) != null) {
            throw new IllegalArgumentException();
        }
        this.dealerRepository.save(Dealer.builder()
                .id(dto.getId())
                .razonSocial(dto.getRazonSocial())
                .cuil(dto.getCuil())
                .address(dto.getAddress())
                .telephone(dto.getTelephone())
                .networks(dto.getNetworks())
                .motorcycles(dto.getMotorcycles())
                .customers(dto.getCustomers())
                .build());
    }
    
}
