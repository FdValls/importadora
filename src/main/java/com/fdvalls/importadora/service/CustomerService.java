package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.OwnerDTO;
import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.repository.OwnerRepository;

public class CustomerService {

    private final OwnerRepository ownerRepository;

    public CustomerService(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

    public OwnerDTO findOwnerById (Long id){
         /**
         * Buscar un Owner en el repositorio (supuestamente una DB)
         * Convertir ese Owner al dto
         */
        Customer owner = this.ownerRepository.findOwnerById(id);

        return this.transformModelToDTO(owner);
    }

    private OwnerDTO transformModelToDTO(Customer model) {
        return new OwnerDTO(model.getId(), model.getName(), model.getLastname(), model.getOld(), model.getIdentification());
    }

    public void saveOwner(OwnerDTO dto){
        if(this.ownerRepository.findOwnerById(dto.getId()) != null){
            throw new IllegalArgumentException();
        }
        if(dto.getIdentification() == null){
            throw new IllegalArgumentException();
        }
        this.ownerRepository.save (Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastname(dto.getLastname())
                .old(dto.getOld())
                .identification(dto.getIdentification())
                .build());
        
    }
    
}
