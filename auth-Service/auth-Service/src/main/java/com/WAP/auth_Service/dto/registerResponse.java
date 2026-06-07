package com.WAP.auth_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class registerResponse {

    private long id;
    private String name;
    private String email;


}
