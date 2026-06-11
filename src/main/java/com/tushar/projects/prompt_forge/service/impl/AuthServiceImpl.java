package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.auth.AuthResponseDTO;
import com.tushar.projects.prompt_forge.dto.auth.LoginRequestDTO;
import com.tushar.projects.prompt_forge.dto.auth.SignUpRequestDTO;
import com.tushar.projects.prompt_forge.entity.User;
import com.tushar.projects.prompt_forge.error.BadRequestException;
import com.tushar.projects.prompt_forge.mapper.UserMapper;
import com.tushar.projects.prompt_forge.reposityory.UserRepository;
import com.tushar.projects.prompt_forge.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO signup(SignUpRequestDTO signUpRequestDTO) {
        userRepository.findByUsername(signUpRequestDTO.username()).ifPresent(user -> {
            throw new BadRequestException("User already exits with username: " + signUpRequestDTO.username());
        });

        User user = userMapper.toEntityFromSignUpRequest(signUpRequestDTO);
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.password()));
        user = userRepository.save(user);
        
        return new AuthResponseDTO("dummy", userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
