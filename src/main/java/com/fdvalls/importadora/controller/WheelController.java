package com.fdvalls.importadora.controller;

import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.dto.WheelDTO;
import com.fdvalls.importadora.service.WheelService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wheel")
public class WheelController {

    private  WheelService wheelService;

    public WheelController(WheelService wheelService){
        this.wheelService = wheelService;
    }

    @PostMapping
    public ResponseEntity<?> createDealer(HttpServletRequest request, @RequestBody WheelDTO wheelDTO)
            throws Exception {
        return ResponseEntity.ok().body(this.wheelService.saveWheel(wheelDTO));
    }

    @GetMapping
    public ResponseEntity<?> getAllDealers() {
        return ResponseEntity.ok().body(this.wheelService.findAllWheels());
    }


    
}
