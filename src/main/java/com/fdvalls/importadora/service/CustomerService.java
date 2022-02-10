package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.repository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerDTO findCustomerById (Long id){
         /**
         * Buscar un Owner en el repositorio (supuestamente una DB)
         * Convertir ese Owner al dto
         */
        Customer owner = this.customerRepository.findCustomerById(id);

        return this.transformModelToDTO(owner);
    }

    private CustomerDTO transformModelToDTO(Customer model) {
        return new CustomerDTO(model.getId(), model.getName(), model.getLastname(), model.getOld(), model.getIdentification());
    }

    public void saveCustomer(CustomerDTO dto){
        if(this.customerRepository.findCustomerById(dto.getId()) != null){
            throw new IllegalArgumentException();
        }
        if(dto.getIdentification() == null){
            throw new IllegalArgumentException();
        }
        this.customerRepository.save (Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastname(dto.getLastname())
                .old(dto.getOld())
                .identification(dto.getIdentification())
                .build());
        
    }
    
}
