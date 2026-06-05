package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.project.FileContentResponseDTO;
import com.tushar.projects.prompt_forge.dto.project.FileNodeDTO;

import java.util.List;

public interface FileService {

    List<FileNodeDTO> getFileTree(Long projectId, Long userId);

    FileContentResponseDTO getFileContent(Long projectId, String path, Long userId);
}
