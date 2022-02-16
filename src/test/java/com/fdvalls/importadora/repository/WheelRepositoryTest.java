package com.fdvalls.importadora.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.fdvalls.importadora.model.Wheel;

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

public class WheelRepositoryTest {

        @Autowired
        private WheelRepository wheelRepository;

        @Test
        void test_findAll_emptyDB() {
                List<Wheel> allWheels = this.wheelRepository.findAll();
                assertTrue(allWheels.isEmpty());
        }

        @Test
        @Sql(scripts = {"/dbscripts/insert_wheel.sql","/dbscripts/insert_motorcycle.sql"})
        void test_findAll() {
                List<Wheel> allWheels = this.wheelRepository.findAll();
                assertFalse(allWheels.isEmpty());
                assertEquals("Michelin Primacy 4 103Y", allWheels.get(0).getMarca());
        }

}
