package com.example.SpringSecurity_CustomCSRF.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping
    public String get(){
        System.out.println("loading index.html");
        return "index";
    }
}
