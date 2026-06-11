package com.tushar.projects.prompt_forge.mapper;

import com.tushar.projects.prompt_forge.dto.auth.SignUpRequestDTO;
import com.tushar.projects.prompt_forge.dto.auth.UserProfileResponseDTO;
import com.tushar.projects.prompt_forge.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntityFromSignUpRequest(SignUpRequestDTO signUpRequestDTO);

    UserProfileResponseDTO toUserProfileResponse(User user);
}
