package com.fdvalls.importadora.repository;

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
    private WheelRepository wheereRepository;

    @Test
    void test_findAll_emptyDB() {
        List<Wheel> allWheels= this.wheereRepository.findAll();
        assertTrue(allWheels.isEmpty());
    }

    @Test
    @Sql(scripts = "/dbscripts/insert_owner.sql")
    void test_findAll() {
        List<Wheel> allWheels = this.wheereRepository.findAll();
        assertFalse(allWheels.isEmpty());
    }

}
