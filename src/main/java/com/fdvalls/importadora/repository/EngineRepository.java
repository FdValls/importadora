package com.fdvalls.importadora.repository;

import java.util.List;
import java.util.Optional;

import com.fdvalls.importadora.model.Engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {

    Optional<Engine> findById(Long id);

    List<Engine> findAll();
    
}
