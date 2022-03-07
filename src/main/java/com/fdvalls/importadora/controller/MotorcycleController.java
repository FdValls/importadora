package com.fdvalls.importadora.controller;

import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.dto.MotorcycleDTO;
import com.fdvalls.importadora.service.MotorcycleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {

    private final MotorcycleService motorcycleService;

    @Autowired
    public MotorcycleController (MotorcycleService motorcycleService){
        this.motorcycleService = motorcycleService;
    }

    @PostMapping
    public MotorcycleDTO createMotorcycle(HttpServletRequest request, @RequestBody MotorcycleDTO motorcycleDTO)
            throws Exception {
        return this.motorcycleService.saveMotorcycle(motorcycleDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllMotorcycles(HttpServletRequest request) {
        return ResponseEntity.ok().body(this.motorcycleService.findAllMotorcycles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(HttpServletRequest request,
            @PathVariable("id") Long id,
            @RequestBody MotorcycleDTO dto) throws Exception{
        return ResponseEntity.ok().body(this.motorcycleService.update(id, dto));
    }
    
}
