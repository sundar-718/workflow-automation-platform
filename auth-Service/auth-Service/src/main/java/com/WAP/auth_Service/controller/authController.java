package com.WAP.auth_Service.controller;


import com.WAP.auth_Service.dto.registerRequest;
import com.WAP.auth_Service.dto.registerResponse;
import com.WAP.auth_Service.service.authServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class authController {

    private final authServiceImpl services;

    public authController(authServiceImpl services){
        this.services=services;
    }

    @PostMapping ("/register")
    public ResponseEntity<String> register(@Valid @RequestBody registerRequest register){

        services.userRegister(register);
        return ResponseEntity.ok("register successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<registerResponse>> getAll(){

        List<registerResponse> User =services.getAllUser();
        return ResponseEntity.ok(User);
    }

    @GetMapping("/{id}")
    public ResponseEntity<registerResponse> getUserById(@PathVariable Long id){

        registerResponse User=services.getUserByid(id);
        return ResponseEntity.ok(User);
    }
}
