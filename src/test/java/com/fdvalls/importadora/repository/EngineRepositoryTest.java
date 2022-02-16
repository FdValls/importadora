package com.fdvalls.importadora.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import com.fdvalls.importadora.model.Engine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@TestPropertySource(properties = {
                "spring.datasource.url=jdbc:h2:mem:testdb",
                "spring.datasource.driverClassName=org.h2.Driver",
                "spring.datasource.username=sa",
                "spring.datasource.password=password",
                "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
})

public class EngineRepositoryTest {

    @Autowired
    private EngineRepository engineRepository;

    @Test
    void test_findAll_emptyDB() {
            List<Engine> allEngines = this.engineRepository.findAll();
            assertFalse(allEngines.isEmpty());
    }

    @Test
    @Sql(scripts = {"/dbscripts/insert_engine.sql"})
    void test_findAll() {
            List<Engine> allWheels = this.engineRepository.findAll();
            assertFalse(allWheels.isEmpty());
            assertEquals("BMW",
            allWheels.get(0).getBrand());
    }
    
}
