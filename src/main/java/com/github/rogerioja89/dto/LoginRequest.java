package com.github.rogerioja89.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "username e obrigatorio")
    private String username;

    @NotBlank(message = "password e obrigatorio")
    private String password;
}

