package com.tushar.projects.prompt_forge.mapper;

import com.tushar.projects.prompt_forge.dto.auth.SignUpRequest;
import com.tushar.projects.prompt_forge.dto.auth.UserProfileResponse;
import com.tushar.projects.prompt_forge.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntityFromSignUpRequest(SignUpRequest signUpRequest);

    UserProfileResponse toUserProfileResponse(User user);
}
