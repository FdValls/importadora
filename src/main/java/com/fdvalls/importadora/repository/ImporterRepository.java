package com.fdvalls.importadora.repository;

import java.util.List;

import com.fdvalls.importadora.model.Importer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImporterRepository extends JpaRepository<Importer, Long>{

    Importer findImporterById(Long id);

    List<Importer> findAll();

    Importer findImporterByCuil(String cuil);

}
