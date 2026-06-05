package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.member.InviteMemberRequestDTO;
import com.tushar.projects.prompt_forge.dto.member.MemberResponseDTO;
import com.tushar.projects.prompt_forge.dto.member.UpdateMemberRoleRequestDTO;

import java.util.List;

public interface ProjectMemberService {

    List<MemberResponseDTO> getMembers(Long projectId, Long userId);

    MemberResponseDTO inviteMember(String projectId, InviteMemberRequestDTO request, Long userId);

    MemberResponseDTO updateMemberRole(String projectId, String memberId, UpdateMemberRoleRequestDTO request, Long userId);

    MemberResponseDTO deleteProjectMember(String projectId, String memberId, Long userId);
}
