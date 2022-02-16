package com.fdvalls.importadora.repository;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.fdvalls.importadora.model.Importer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@SpringBootTest
@TestPropertySource(properties = {
                "spring.datasource.url=jdbc:h2:mem:testdb",
                "spring.datasource.driverClassName=org.h2.Driver",
                "spring.datasource.username=sa",
                "spring.datasource.password=password",
                "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
})

@Transactional
public class ImporterRepositoryTest {
    
    @Autowired
    private ImporterRepository importerRepository;

    @Test
    void test_findAll_emptyDB() {
            List<Importer> allImports = this.importerRepository.findAll();
            assertTrue(allImports.isEmpty());
    }

    @Test
    @Sql(scripts = {"/dbscripts/insert_importer.sql"})
    void test_findAll() {
            List<Importer> allImporters = this.importerRepository.findAll();
            assertFalse(allImporters.isEmpty());
            assertEquals("WallStreer123",
            allImporters.get(0).getAddress());
    }
}
