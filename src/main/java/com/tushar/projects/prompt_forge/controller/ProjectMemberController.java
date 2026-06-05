package com.tushar.projects.prompt_forge.controller;

import com.tushar.projects.prompt_forge.dto.member.InviteMemberRequestDTO;
import com.tushar.projects.prompt_forge.dto.member.MemberResponseDTO;
import com.tushar.projects.prompt_forge.dto.member.UpdateMemberRoleRequestDTO;
import com.tushar.projects.prompt_forge.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects/{projectId}/member")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectMemberController {

    ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getProjectMember(@PathVariable Long projectId) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.getMembers(projectId, userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> inviteMember(@PathVariable Long projectId, @RequestBody InviteMemberRequestDTO request) {
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.inviteMember(projectId, request, userId));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> updateMemberRole(@PathVariable Long projectId, @PathVariable Long memberId, @RequestBody UpdateMemberRoleRequestDTO request) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, request, userId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> deleteMember(@PathVariable Long projectId, @PathVariable Long memberId) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.deleteProjectMember(projectId, memberId, userId));
    }


}
