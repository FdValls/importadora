package com.fdvalls.importadora.repository;

import com.fdvalls.importadora.model.Dealer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long>{

    Dealer findDealerById(Long id);

    Dealer findDealerByCuil (String cuil);
    
}
