package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.auth.AuthResponseDTO;
import com.tushar.projects.prompt_forge.dto.auth.LoginRequestDTO;
import com.tushar.projects.prompt_forge.dto.auth.SignUpRequestDTO;

public interface AuthService {

    AuthResponseDTO signup(SignUpRequestDTO signUpRequestDTO);

    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
}
