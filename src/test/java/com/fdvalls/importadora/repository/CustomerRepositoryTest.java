package com.fdvalls.importadora.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import com.fdvalls.importadora.model.Customer;

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
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void test_findAll_emptyDB() {
        assertTrue(this.customerRepository.findAll().isEmpty());
    }

    @Test
    @Sql(scripts = "/dbscripts/insert_customer.sql")
    void test_findAll() {
            List<Customer> customers = this.customerRepository.findAll();
            assertFalse(customers.isEmpty());
            
            Customer customer = customers.get(0);
            assertEquals("Fernando", customer.getName());
    }

}
