package com.fdvalls.importadora.repository;

import java.util.List;

import com.fdvalls.importadora.model.Owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findOwnerById(Long id);

    List<Owner> findAll();

}
