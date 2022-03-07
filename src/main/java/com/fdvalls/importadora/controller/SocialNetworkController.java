package com.fdvalls.importadora.controller;

import javax.servlet.http.HttpServletRequest;

import com.fdvalls.importadora.service.SocialNetworkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network")
public class SocialNetworkController {

    private final SocialNetworkService socialNetworkService;

    @Autowired
    public SocialNetworkController(SocialNetworkService socialNetworkService) {
        this.socialNetworkService = socialNetworkService;
    }

    @GetMapping
    public ResponseEntity<?> getAllSocialNetworks(HttpServletRequest request)
            throws Exception {
        return ResponseEntity.ok().body(this.socialNetworkService.findAllNetwork());

    }
}
