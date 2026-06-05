package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.auth.UserProfileResponseDTO;
import com.tushar.projects.prompt_forge.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserProfileResponseDTO getProfile(Long userId) {
        return null;
    }
}
