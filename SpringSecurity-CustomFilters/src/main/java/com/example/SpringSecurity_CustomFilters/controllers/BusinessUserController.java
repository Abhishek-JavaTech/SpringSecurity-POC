package com.example.SpringSecurity_CustomFilters.controllers;

import com.example.SpringSecurity_CustomFilters.model.BusinessUser;
import com.example.SpringSecurity_CustomFilters.repos.BusinessUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.SpringSecurity_CustomFilters.constants.EncryptAlgo.SCRYPT;

@RestController
@RequestMapping("/users")
public class BusinessUserController {

    private BusinessUserRepository userRepository;
    private PasswordEncoder bCryptPasswordEncoder;
    private PasswordEncoder sCryptPasswordEncoder;

    public BusinessUserController(BusinessUserRepository userRepository, PasswordEncoder sCryptPasswordEncoder, PasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sCryptPasswordEncoder = sCryptPasswordEncoder;
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody BusinessUser businessUser){
        try{
            var encPwd = getPasswordEncoder(businessUser.getEncrAlgo()).encode(businessUser.getPassword());
            var pwd = businessUser.getEncrAlgo().equalsIgnoreCase(SCRYPT) ? "{scrypt}" + encPwd : "{bcrypt}" + encPwd;
            businessUser.setPassword(pwd);

            userRepository.save(businessUser);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public ResponseEntity getAllUser(Authentication authentication){
        try{
            System.out.println("Authentication is done for: "+authentication.getPrincipal());
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try{
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody BusinessUser businessUser){
        try{
            userRepository.save(businessUser);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private PasswordEncoder getPasswordEncoder(String encrAlgo){
        switch (encrAlgo){
            case SCRYPT:
                return sCryptPasswordEncoder;
            default:
                return bCryptPasswordEncoder;
        }
    }
}
