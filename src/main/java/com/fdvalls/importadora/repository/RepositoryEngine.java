package com.fdvalls.importadora.repository;

import com.fdvalls.importadora.model.Engine;

import org.springframework.data.repository.CrudRepository;

public interface RepositoryEngine extends CrudRepository<Engine, Long> {
    
}
