package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.action.NewRuleAction;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO findCustomerById (Long id) {
         /**
         * Buscar un Customer en el repositorio (supuestamente una DB)
         * Convertir ese Customer al dto
         */
        Customer customer = this.customerRepository.findCustomerById(id);
        if(customer != null){
            return this.transformModelToDTO(customer);
        }
        return null;
    }

    private CustomerDTO transformModelToDTO(Customer model) {
        return new CustomerDTO(model.getId(), model.getName(), model.getLastname(), model.getAge(), model.getIdentification());
    }

    public CustomerDTO saveCustomer(CustomerDTO dto) throws Exception{
        if(this.customerRepository.findByIdentification(dto.getIdentification()) != null){
            throw new Exception("Identification already exists: "+dto.getIdentification());
        }
        Customer resultado = this.customerRepository.save(Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastname(dto.getLastname())
                .age(dto.getAge())
                .identification(dto.getIdentification())
                .build());
        
        return this.transformModelToDTO(resultado);
    }

    public List<Customer> findAllCustomers () throws Exception{
        if(this.customerRepository.findAll() == null){
            throw new Exception("List null");
        }
        List<Customer> customers = this.customerRepository.findAll();
        return customers;
    }

    public void update(Long id, String newName) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		Customer customerUpdate = this.customerRepository.findCustomerById(id);
		
		this.customerRepository.update(Customer.builder()
				.id(id)
				.name(newName)
                .lastname(customerUpdate.getLastname())
                .identification(customerUpdate.getIdentification())
				.age(customerUpdate.getAge())
				.build());
	}

}
