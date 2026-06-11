package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.auth.AuthResponse;
import com.tushar.projects.prompt_forge.dto.auth.LoginRequest;
import com.tushar.projects.prompt_forge.dto.auth.SignUpRequest;
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
    public AuthResponse signup(SignUpRequest signUpRequest) {
        userRepository.findByUsername(signUpRequest.username()).ifPresent(user -> {
            throw new BadRequestException("User already exits with username: " + signUpRequest.username());
        });

        User user = userMapper.toEntityFromSignUpRequest(signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user = userRepository.save(user);

        return new AuthResponse("dummy", userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }
}
