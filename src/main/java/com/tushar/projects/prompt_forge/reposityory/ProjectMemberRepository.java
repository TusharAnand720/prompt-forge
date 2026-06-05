package com.tushar.projects.prompt_forge.reposityory;

import com.tushar.projects.prompt_forge.entity.ProjectMember;
import com.tushar.projects.prompt_forge.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByProjectId(Long projectId);
}
