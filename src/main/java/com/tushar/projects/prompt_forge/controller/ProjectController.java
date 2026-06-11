package com.tushar.projects.prompt_forge.controller;

import com.tushar.projects.prompt_forge.dto.project.ProjectRequest;
import com.tushar.projects.prompt_forge.dto.project.ProjectResponse;
import com.tushar.projects.prompt_forge.dto.project.ProjectSummaryResponse;
import com.tushar.projects.prompt_forge.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/project")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectController {

    ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProject() {
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getUserProjectById(id, userId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest projectRequest) {
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.createProject(projectRequest, userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id,
                                                         @RequestBody @Valid ProjectRequest projectRequest) {
        Long userId = 1L;
        return ResponseEntity.ok(projectService.updateProject(id, projectRequest, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        Long userId = 1L;
        projectService.deleteProject(id, userId);
        return ResponseEntity.noContent().build();
    }
}
