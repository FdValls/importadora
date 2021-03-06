package com.fdvalls.importadora.repository;


import com.fdvalls.importadora.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerById(Long id);

    Customer findByIdentification(String identification);

}
