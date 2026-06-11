package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.auth.AuthResponse;
import com.tushar.projects.prompt_forge.dto.auth.LoginRequest;
import com.tushar.projects.prompt_forge.dto.auth.SignUpRequest;

public interface AuthService {

    AuthResponse signup(SignUpRequest signUpRequest);

    AuthResponse login(LoginRequest loginRequest);
}
