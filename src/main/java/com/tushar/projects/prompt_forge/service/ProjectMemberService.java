package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.member.InviteMemberRequestDTO;
import com.tushar.projects.prompt_forge.dto.member.MemberResponseDTO;
import com.tushar.projects.prompt_forge.dto.member.UpdateMemberRoleRequestDTO;

import java.util.List;

public interface ProjectMemberService {

    List<MemberResponseDTO> getMembers(Long projectId, Long userId);

    MemberResponseDTO inviteMember(Long projectId, InviteMemberRequestDTO request, Long userId);

    MemberResponseDTO updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequestDTO request, Long userId);

    MemberResponseDTO deleteProjectMember(Long projectId, Long memberId, Long userId);
}
