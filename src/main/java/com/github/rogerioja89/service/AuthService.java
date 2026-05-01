package com.github.rogerioja89.service;

import com.github.rogerioja89.dto.TokenResponse;

public interface AuthService {

    TokenResponse autenticar(String username, String password);
}

