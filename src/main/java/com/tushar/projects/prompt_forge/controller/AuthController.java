package com.tushar.projects.prompt_forge.controller;

import com.tushar.projects.prompt_forge.dto.auth.AuthResponseDTO;
import com.tushar.projects.prompt_forge.dto.auth.LoginRequestDTO;
import com.tushar.projects.prompt_forge.dto.auth.SignUpRequestDTO;
import com.tushar.projects.prompt_forge.dto.auth.UserProfileResponseDTO;
import com.tushar.projects.prompt_forge.service.AuthService;
import com.tushar.projects.prompt_forge.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthService authService;
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(authService.signup(signUpRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponseDTO> getProfile() {
        long userId = 0L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }
}
