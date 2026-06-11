package com.tushar.projects.prompt_forge.mapper;

import com.tushar.projects.prompt_forge.dto.member.MemberResponseDTO;
import com.tushar.projects.prompt_forge.entity.ProjectMember;
import com.tushar.projects.prompt_forge.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponseDTO toProjectMemberResponseFromUser(User user);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    MemberResponseDTO toProjectMemberResponseFromMember(ProjectMember projectMember);
}
