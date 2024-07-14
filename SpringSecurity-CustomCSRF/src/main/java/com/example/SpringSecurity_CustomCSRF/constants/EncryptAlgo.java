package com.example.SpringSecurity_CustomCSRF.constants;

import lombok.Data;

@Data
public class EncryptAlgo {

    public static final String BCRYPT = "bcrypt";
    public static final String SCRYPT = "scrypt";
}
