package com.fdvalls.importadora.controller;

import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.dto.DealerDTO;
import com.fdvalls.importadora.exception.AlreadyExists;
import com.fdvalls.importadora.exception.NotFoundException;
import com.fdvalls.importadora.service.DealerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dealer")
public class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping
    public ResponseEntity<?> createDealer(HttpServletRequest request, @RequestBody DealerDTO dealerDTO)
            throws Exception {
        try {
            return ResponseEntity.ok().body(this.dealerService.saveDealer(dealerDTO));
        } catch (Exception e) {
            throw new AlreadyExists(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllDealers() throws Exception {
        return ResponseEntity.ok().body(this.dealerService.findAllDealers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request,
            @PathVariable("id") Long id) throws Exception {
        try {
            return ResponseEntity.ok().body(this.dealerService.delete(id));
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
