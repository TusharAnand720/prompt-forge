package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.auth.AuthResponseDTO;
import com.tushar.projects.prompt_forge.dto.auth.LoginRequestDTO;
import com.tushar.projects.prompt_forge.dto.auth.SignUpRequestDTO;
import com.tushar.projects.prompt_forge.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public AuthResponseDTO signup(SignUpRequestDTO signUpRequestDTO) {
        return null;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
