package com.fdvalls.importadora.controller;

import com.fdvalls.importadora.repository.RepositoryEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerHome {

    @Autowired
    private RepositoryEngine engineDAO;

    @GetMapping("/")
    public String inicio(Model model){
        var engine = engineDAO.findAll();
        model.addAttribute("engine", engine);
        return "index";
    }
    
}
