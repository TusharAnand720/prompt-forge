package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
