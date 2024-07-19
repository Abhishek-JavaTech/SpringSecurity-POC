package com.example.SS_PrePostAnnotations.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class ResourceController {

    @GetMapping("/index")
    public String index(Authentication authentication) {
        return "Index: "+authentication.getName();
    }

    @PreAuthorize("hasAuthority('reader')")
    @GetMapping("/preAuth/{name}")
    public String preAuth(@PathVariable String name) {
        System.out.println("preAuth method executed");
        return "User preAuth: " + name;
    }

    @PostAuthorize("hasAnyAuthority('admin', 'writer')")
    @GetMapping("/postAuth/{name}")
    public String postAuth(@PathVariable String name) {
        System.out.println("postAuth method executed");
        return "User postAuth: " + name;
    }

    @Secured("ROLE_reader")
    @GetMapping("/secured/{name}")
    public String secured(@PathVariable String name) {
        System.out.println("secured method executed");
        return "User Secured: " + name;
    }

    @RolesAllowed({"ROLE_admin", "ROLE_writer"})
    @GetMapping("/rolesAllowed/{name}")
    public String rolesAllowed(@PathVariable String name) {
        System.out.println("rolesAllowed method executed");
        return "User rolesAllowed: " + name;
    }

//    @PreFilter("filterObject == authentication.principal.username")
    @PreFilter("filterObject != authentication.principal.username")
    @GetMapping("/preFilter")
    public List<String> preFilter(@RequestBody List<String> list) {
        System.out.println("preFilter method executed");
        return list;
    }

//    @PostFilter("filterObject == authentication.principal.username")
    @PostFilter("filterObject != authentication.principal.username")
    @GetMapping("/postFilter")
    public List<String>  postFilter() {
        System.out.println("postFilter method executed");
        List<String> list = new ArrayList<>();
        list.add("reader");
        list.add("writer");
        list.add("admin");
        list.add("none");
        return list;
    }
}


