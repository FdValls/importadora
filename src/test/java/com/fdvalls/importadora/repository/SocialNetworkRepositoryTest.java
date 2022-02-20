package com.fdvalls.importadora.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import com.fdvalls.importadora.model.SocialNetwork;

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
public class SocialNetworkRepositoryTest {
    
    @Autowired
    private NetworkRepository networkRepository;

    @Test
    void test_findAll_emptyDB() {
            List<SocialNetwork> allNetworks = this.networkRepository.findAll();
            assertTrue(allNetworks.isEmpty());
    }

    @Test
    @Sql(scripts = {"/dbscripts/insert_social_network.sql"})
    void test_findAll() {
            List<SocialNetwork> allNetworks = this.networkRepository.findAll();
            assertFalse(allNetworks.isEmpty());
            assertEquals(1L,
            allNetworks.get(0).getId());
    }


}
