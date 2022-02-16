package com.fdvalls.importadora.repository;

import java.util.List;

import com.fdvalls.importadora.model.Engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {

    Engine findEngineById(Long id);

    List<Engine> findAll();
    
}
