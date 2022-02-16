package com.fdvalls.importadora.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import com.fdvalls.importadora.model.Dealer;
import java.util.List;
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

@Transactional
public class DealerRepositoryTest {
    
    @Autowired
    private DealerRepository  dealerRepository;

    @Test
    void test_findAll_emptyDB() {
        assertTrue(this.dealerRepository.findAll().isEmpty());
    }

    @Test
    @Sql(scripts = {"/dbscripts/insert_dealer.sql","/dbscripts/insert_network.sql","/dbscripts/insert_motorcycle.sql"})
    void test_findAll() {
            List<Dealer> dealers = this.dealerRepository.findAll();
            assertFalse(dealers.isEmpty());
            
            assertEquals(1, dealers.get(0).getId());
    }
}
