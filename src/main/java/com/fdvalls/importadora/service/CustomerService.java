package com.fdvalls.importadora.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fdvalls.importadora.dto.CustomerDTO;
import com.fdvalls.importadora.exception.AlreadyExists;
import com.fdvalls.importadora.exception.DealerNotFoundException;
import com.fdvalls.importadora.exception.MotorcycleNotFoundException;
import com.fdvalls.importadora.exception.NotExist;
import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.model.Motorcycle;
import com.fdvalls.importadora.repository.CustomerRepository;
import com.fdvalls.importadora.repository.DealerRepository;
import com.fdvalls.importadora.repository.MotorcycleRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final DealerRepository dealerRepository;
    private final MotorcycleRepository motorcycleRepository;

    public CustomerService(CustomerRepository customerRepository, DealerRepository dealerRepository,
            MotorcycleRepository motorcycleRepository) {
        this.customerRepository = customerRepository;
        this.dealerRepository = dealerRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    public CustomerDTO findCustomerById(Long id) {
        Customer customer = this.customerRepository.findCustomerById(id);
        if (customer != null) {
            return this.transformModelToDTO(customer);
        }
        return null;
    }

    private CustomerDTO transformModelToDTO(Customer model) {
        return CustomerDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .lastname(model.getLastname())
                .age(model.getAge())
                .identification(model.getIdentification())
                .build();
    }

    @Transactional
    public CustomerDTO saveCustomer(CustomerDTO dto) throws Exception {
        if (this.customerRepository.findByIdentification(dto.getIdentification()) != null) {
            throw new AlreadyExists("Identification already exists: " + dto.getIdentification());
        }

        Optional<Dealer> dealerOptional = this.dealerRepository.findById(dto.getDealerId());
        Dealer dealer = dealerOptional.orElseThrow(
                () -> new DealerNotFoundException("Dealer with id " + dto.getDealerId() + " does not exists"));

        Optional<Motorcycle> motorcycleOptional = this.motorcycleRepository.findById(dto.getMotorcycleId());
        Motorcycle motorcycle = motorcycleOptional.orElseThrow(
                () -> new MotorcycleNotFoundException(
                        "Motorcycle with id " + dto.getMotorcycleId() + " does not exists"));

        Customer customer = Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastname(dto.getLastname())
                .age(dto.getAge())
                .identification(dto.getIdentification())
                .build();

        customer = this.customerRepository.save(customer);
        dealer.getCustomers().add(customer);
        motorcycle.getCustomers().add(customer);
        this.dealerRepository.save(dealer);

        return this.transformModelToDTO(customer);
    }

    public List<CustomerDTO> findAllCustomers() {
        return this.customerRepository.findAll().stream().map(this::transformModelToDTO).toList();
    }

    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer customerUpdate = this.customerRepository.findCustomerById(id);
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        } else if (customerUpdate == null) {
            throw new IllegalArgumentException("customer not exist");
        } else {
            return this.transformModelToDTO(this.customerRepository.save(Customer.builder()
                    .id(customerUpdate.getId())
                    .name(dto.getName())
                    .lastname(dto.getLastname())
                    .identification(dto.getIdentification())
                    .age(dto.getAge())
                    .build()));
        }

    }

    public CustomerDTO delete(Long id) throws Exception {
        Customer customerDetele = this.customerRepository.findCustomerById(id);
        if (customerDetele == null) {
            throw new NotExist("id not exist");
        }
        this.customerRepository.delete(customerDetele);
        return this.transformModelToDTO(customerDetele);
    
    }

}
