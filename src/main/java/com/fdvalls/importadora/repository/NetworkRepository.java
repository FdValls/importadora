package com.fdvalls.importadora.repository;

import java.util.List;

import com.fdvalls.importadora.model.Network;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepository extends JpaRepository<Network, Long>{
    
    Network findNetworkById (Long id);

    List<Network> findAll();

}
