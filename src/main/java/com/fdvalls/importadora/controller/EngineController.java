package com.fdvalls.importadora.controller;

import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.dto.EngineDTO;
import com.fdvalls.importadora.exception.AlreadyExists;
import com.fdvalls.importadora.exception.NotExist;
import com.fdvalls.importadora.exception.NotFoundException;
import com.fdvalls.importadora.service.EngineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engine")
public class EngineController {

    private final EngineService engineService;

    @Autowired
    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping
    public ResponseEntity<?> createrEngine(HttpServletRequest request, @RequestBody EngineDTO engineDTO)
            throws Exception {
        try {
            return ResponseEntity.ok().body(this.engineService.saveEngine(engineDTO));
        } catch (Exception e) {
            throw new AlreadyExists(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllEngines() throws Exception {
        return ResponseEntity.ok().body(this.engineService.findAllEngines());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request,
            @PathVariable("id") Long id) throws Exception {
        try {
            return ResponseEntity.ok().body(this.engineService.delete(id));
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(HttpServletRequest request,
            @PathVariable("id") Long id,
            @RequestBody EngineDTO dto) throws Exception {
        try {
            return ResponseEntity.ok().body(this.engineService.update(id, dto));
        } catch (Exception e) {
            throw new NotExist(e.getMessage());
        }
    }

}
