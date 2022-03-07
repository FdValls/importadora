package com.fdvalls.importadora.controller;

import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.dto.ImporterDTO;
import com.fdvalls.importadora.service.ImporterService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/importer")
public class ImporterController {

    private final ImporterService importerService;

    public ImporterController(ImporterService importerService) {
        this.importerService = importerService;
    }

    @PostMapping
    public ResponseEntity<?> createImporter(HttpServletRequest request, @RequestBody ImporterDTO dealerDTO)
            throws Exception {
        return ResponseEntity.ok().body(this.importerService.saveImporter(dealerDTO));

    }

    @GetMapping
    public ResponseEntity<?> getAllImporters() {
        return ResponseEntity.ok().body(this.importerService.getAllImporters());
    }

}
