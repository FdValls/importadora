package com.fdvalls.importadora.repository;

import com.fdvalls.importadora.model.Motorcycle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long>{

    Motorcycle findByDomain(String domain);

}
