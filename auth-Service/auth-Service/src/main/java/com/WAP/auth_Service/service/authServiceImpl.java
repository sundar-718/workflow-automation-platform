package com.WAP.auth_Service.service;

import com.WAP.auth_Service.dto.loginRequest;
import com.WAP.auth_Service.dto.loginResponse;
import com.WAP.auth_Service.dto.registerRequest;
import com.WAP.auth_Service.dto.registerResponse;
import com.WAP.auth_Service.exception.EmailAlreadyExistsException;
import com.WAP.auth_Service.exception.UserNotFoundException;
import com.WAP.auth_Service.mapper.UserMapper;
import com.WAP.auth_Service.model.user;
import com.WAP.auth_Service.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    Logger log= LoggerFactory.getLogger(authServiceImpl.class);

    public authServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, UserMapper mapper){

        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
        this.mapper=mapper;
    }


    public void userRegister(registerRequest registered){

        if(repository.existsByEmail(registered.getEmail())){
            throw new EmailAlreadyExistsException("email already exist");
        }

        user User=mapper.toEntity(registered);

        String hashedPassword=passwordEncoder.encode(registered.getPassword());
        log.info("password encryted successfully");

        User.setPassword(hashedPassword);
        log.info("user register in database");

        repository.save(User);
    }

    public List<registerResponse> getAllUser(){

        List<user> users = repository.findAll();

        log.info("successfully got all user from database");
        return users.stream()
                .map(mapper::toResponse)
                .toList();
    }

    public registerResponse getUserByid(Long id){

        user User= repository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));

        log.info("successfully got the user with id {}",id);
        return mapper.toResponse(User);
    }

    public Boolean userLogin(loginRequest login){

        String username=login.getName();
        log.info("username:{}",username);

        loginResponse User=repository.finduserByName(username);
        log.info("hashedPassword:{} ",login.getPassword());

        return passwordEncoder.matches(
                login.getPassword(),
                User.getPassword()
        );
    }

}
