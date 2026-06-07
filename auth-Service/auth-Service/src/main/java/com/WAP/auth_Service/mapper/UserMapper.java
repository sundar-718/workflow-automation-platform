package com.WAP.auth_Service.mapper;

import com.WAP.auth_Service.dto.registerRequest;
import com.WAP.auth_Service.dto.registerResponse;
import com.WAP.auth_Service.model.user;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    user toEntity(registerRequest request);

    registerResponse toResponse(user User);
}
