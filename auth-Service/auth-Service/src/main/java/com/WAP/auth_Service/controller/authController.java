package com.WAP.auth_Service.controller;


import com.WAP.auth_Service.dto.loginRequest;
import com.WAP.auth_Service.dto.registerRequest;
import com.WAP.auth_Service.dto.registerResponse;
import com.WAP.auth_Service.exception.InvalidPasswordException;
import com.WAP.auth_Service.response.ApiResponse;
import com.WAP.auth_Service.service.authServiceImpl;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class authController {

    private final authServiceImpl services;

    Logger log= LoggerFactory.getLogger(authController.class);

    public authController(authServiceImpl services){
        this.services=services;

    }

    @PostMapping ("/register")
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody registerRequest register){

        services.userRegister(register);
        log.info("register successfully");

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "User registered successfully",
                        null
                )
        );
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<registerResponse>>> getAll(){

        List<registerResponse> User =services.getAllUser();
        log.info("returned all user");

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Users fetched successfully",
                        User
                )
        );
    }

    @GetMapping("users/{id}")
    public ResponseEntity<ApiResponse<registerResponse>> getUserById(@PathVariable Long id){

        registerResponse User=services.getUserByid(id);
        log.info("returned the user with id {}",id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "User fetched successfully",
                        User
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody loginRequest login){

        if(services.userLogin(login)){
            log.info("login successfully");
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            HttpStatus.OK.value(),
                            "User login successfully",
                            null
                    )
            );
        }
        else{
            log.error("login failed");
            throw new InvalidPasswordException("invalid password");
        }

    }
}
