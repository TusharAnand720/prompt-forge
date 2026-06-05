package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.auth.UserProfileResponseDTO;

public interface UserService {
    UserProfileResponseDTO getProfile(Long userId);
}
