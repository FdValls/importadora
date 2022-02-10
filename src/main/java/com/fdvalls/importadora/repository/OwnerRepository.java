package com.fdvalls.importadora.repository;

import java.util.List;

import com.fdvalls.importadora.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Customer, Long> {

    Customer findOwnerById(Long id);

    List<Customer> findAll();

}