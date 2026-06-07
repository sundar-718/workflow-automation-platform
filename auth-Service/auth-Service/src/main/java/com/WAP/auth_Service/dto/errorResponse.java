package com.WAP.auth_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class errorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
}
