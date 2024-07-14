package com.example.SpringSecurity_CustomFilters.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class BusinessUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private boolean enabled = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BusinessUserAuthorities> businessUserAuthorities = new ArrayList<>();
    private String encrAlgo; // values can be either BRCYPT or SCRYPT
}
