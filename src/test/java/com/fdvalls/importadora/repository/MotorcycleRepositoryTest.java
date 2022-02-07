package com.fdvalls.importadora.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.fdvalls.importadora.model.Motorcycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootApplication
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=password",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
})
public class MotorcycleRepositoryTest {

    @Autowired
    private MotorcycleRepository motorcycleRepository;
    
    @Test
    void test_findAll_emptyDB() {
        List<Motorcycle> allMotorcycle= this.motorcycleRepository.findAll();
        assertTrue(allMotorcycle.isEmpty());
    }

    @Test
    @Sql(scripts = "/dbscripts/insert_owner.sql")
    void test_findAll() {
        List<Motorcycle> allMotorcycle = this.motorcycleRepository.findAll();
        assertFalse(allMotorcycle.isEmpty());
    }
    
}
