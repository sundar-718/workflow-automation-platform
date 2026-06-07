package com.WAP.auth_Service.service;

import com.WAP.auth_Service.dto.registerRequest;
import com.WAP.auth_Service.dto.registerResponse;

import java.util.List;

public interface AuthService {

    void userRegister(registerRequest registered);

    List<registerResponse> getAllUser();

    registerResponse getUserByid(Long id);
}
