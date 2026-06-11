package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.member.InviteMemberRequest;
import com.tushar.projects.prompt_forge.dto.member.MemberResponse;
import com.tushar.projects.prompt_forge.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {

    List<MemberResponse> getMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);

    void removeProjectMember(Long projectId, Long memberId, Long userId);
}
