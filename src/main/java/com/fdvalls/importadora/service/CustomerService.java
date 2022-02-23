package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.repository.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerDTO findCustomerById (Long id){
         /**
         * Buscar un Customer en el repositorio (supuestamente una DB)
         * Convertir ese Customer al dto
         */
        Customer customer = this.customerRepository.findCustomerById(id);

        return this.transformModelToDTO(customer);
    }

    private CustomerDTO transformModelToDTO(Customer model) {
        return new CustomerDTO(model.getId(), model.getName(), model.getLastname(), model.getOld(), model.getIdentification());
    }

    public String saveCustomer(CustomerDTO dto){
        if(this.findCustomerById(dto.getId()) != null){
            this.customerRepository.save (Customer.builder()
            .id(dto.getId())
            .name(dto.getName())
            .lastname(dto.getLastName())
            .old(dto.getOld())
            .identification(dto.getIdentification())
            .build());
            
            return "ok";
        }
        
        return "NOK";
    }
    
}
