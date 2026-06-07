package com.WAP.auth_Service.service;

import com.WAP.auth_Service.dto.registerRequest;
import com.WAP.auth_Service.dto.registerResponse;
import com.WAP.auth_Service.mapper.UserMapper;
import com.WAP.auth_Service.model.user;
import com.WAP.auth_Service.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public authServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, UserMapper mapper){

        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
        this.mapper=mapper;
    }


    public void userRegister(registerRequest registered){

        user User=mapper.toEntity(registered);

        String hashedPassword=passwordEncoder.encode(registered.getPassword());
        User.setPassword(hashedPassword);
        repository.save(User);
    }

    public List<registerResponse> getAllUser(){

        List<user> users = repository.findAll();

        return users.stream()
                .map(mapper::toResponse)
                .toList();
    }

    public registerResponse getUserByid(Long id){

        user User= repository.findById(id).orElseThrow(RuntimeException::new);

        return mapper.toResponse(User);
    }

}
