package com.fdvalls.importadora.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.fdvalls.importadora.model.Motorcycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource(properties = {
                "spring.datasource.url=jdbc:h2:mem:testdb",
                "spring.datasource.driverClassName=org.h2.Driver",
                "spring.datasource.username=sa",
                "spring.datasource.password=password",
                "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
})
@Transactional
public class MotorcycleRepositoryTest {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Test
    void test_findAll_emptyDB() {
        assertTrue(this.motorcycleRepository.findAll().isEmpty());
    }

    @Test
    @Sql(scripts = {"/dbscripts/insert_motorcycle.sql","/dbscripts/insert_wheel.sql","/dbscripts/insert_engine.sql"})
    void test_findAll() {
            List<Motorcycle> motorcycles = this.motorcycleRepository.findAll();
            assertFalse(motorcycles.isEmpty());
            
            Motorcycle motorcycle = motorcycles.get(0);
            assertFalse(motorcycle.getWheels() != null);
            assertEquals("Michelin Primacy 4 103Y", motorcycle.getBrand());
    }
    
}
