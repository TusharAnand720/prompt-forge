package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.member.InviteMemberRequestDTO;
import com.tushar.projects.prompt_forge.dto.member.MemberResponseDTO;
import com.tushar.projects.prompt_forge.dto.member.UpdateMemberRoleRequestDTO;
import com.tushar.projects.prompt_forge.entity.Project;
import com.tushar.projects.prompt_forge.entity.ProjectMember;
import com.tushar.projects.prompt_forge.entity.ProjectMemberId;
import com.tushar.projects.prompt_forge.entity.User;
import com.tushar.projects.prompt_forge.mapper.ProjectMemberMapper;
import com.tushar.projects.prompt_forge.reposityory.ProjectMemberRepository;
import com.tushar.projects.prompt_forge.reposityory.ProjectRepository;
import com.tushar.projects.prompt_forge.reposityory.UserRepository;
import com.tushar.projects.prompt_forge.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    UserRepository userRepository;

    ProjectMemberMapper projectMemberMapper;

    @Override
    public List<MemberResponseDTO> getMembers(Long projectId, Long userId) {
        List<MemberResponseDTO> memberResponseList = new ArrayList<>();

        Project project = getAccessibleProjectById(projectId, userId);

        memberResponseList.add(projectMemberMapper.toProjectMemberResponseFromUser(project.getOwner()));

        List<MemberResponseDTO> projectMemberList = projectMemberRepository.findByProjectId(projectId).stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList();

        memberResponseList.addAll(projectMemberList);

        return memberResponseList;
    }

    @Override
    public MemberResponseDTO inviteMember(Long projectId, InviteMemberRequestDTO request, Long userId) {

        Project project = getAccessibleProjectById(projectId, userId);

        User invitee = userRepository.findByEmail(request.email()).orElseThrow();
        if (invitee.getId().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, userId);
        if (projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Connot invite once again");
        }

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public MemberResponseDTO updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequestDTO request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not allowed");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setProjectRole(request.role());

        projectMember = projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not allowed");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);

        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        if (projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Member not found");
        }
        projectMemberRepository.deleteById(projectMemberId);
    }

    // === Internal Functions ===

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
