package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.member.InviteMemberRequestDTO;
import com.tushar.projects.prompt_forge.dto.member.MemberResponseDTO;
import com.tushar.projects.prompt_forge.dto.member.UpdateMemberRoleRequestDTO;
import com.tushar.projects.prompt_forge.service.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Override
    public List<MemberResponseDTO> getMembers(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public MemberResponseDTO inviteMember(String projectId, InviteMemberRequestDTO request, Long userId) {
        return null;
    }

    @Override
    public MemberResponseDTO updateMemberRole(String projectId, String memberId, UpdateMemberRoleRequestDTO request, Long userId) {
        return null;
    }

    @Override
    public MemberResponseDTO deleteProjectMember(String projectId, String memberId, Long userId) {
        return null;
    }
}
