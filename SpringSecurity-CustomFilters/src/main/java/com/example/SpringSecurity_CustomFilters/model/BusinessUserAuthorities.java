package com.example.SpringSecurity_CustomFilters.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name = "authorities")
@NoArgsConstructor
public class BusinessUserAuthorities implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String authority;

    @ManyToOne
    private BusinessUser user;

    public BusinessUserAuthorities(String authority, String username){
        this.authority = authority;
        this.username = username;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
