package com.example.SpringSecurity_CustomFilters.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/")
    public String get(){
        return "Hello World!";
    }
}
