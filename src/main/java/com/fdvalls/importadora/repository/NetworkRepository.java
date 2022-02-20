package com.fdvalls.importadora.repository;

import java.util.List;

import com.fdvalls.importadora.model.SocialNetwork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepository extends JpaRepository<SocialNetwork, Long>{
    
    SocialNetwork findNetworkById (Long id);

    List<SocialNetwork> findAll();

}
