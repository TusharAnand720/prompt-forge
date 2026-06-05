package com.tushar.projects.prompt_forge.controller;

import com.tushar.projects.prompt_forge.dto.project.FileContentResponseDTO;
import com.tushar.projects.prompt_forge.dto.project.FileNodeDTO;
import com.tushar.projects.prompt_forge.service.FileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/projects/{projectId}/files")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FileController {

    FileService fileService;

    @GetMapping("/{id}")
    public ResponseEntity<List<FileNodeDTO>> getFileTree(@PathVariable Long projectId) {
        Long userId = 0L;
        return ResponseEntity.ok(fileService.getFileTree(projectId, userId));
    }

    @GetMapping("{*path}")
    public ResponseEntity<FileContentResponseDTO> getFile(@PathVariable Long projectId, @PathVariable String path) {
        Long userId = 0L;
        return ResponseEntity.ok(fileService.getFileContent(projectId, path, userId));
    }
}
