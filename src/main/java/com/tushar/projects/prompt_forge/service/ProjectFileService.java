package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.project.FileContentResponse;
import com.tushar.projects.prompt_forge.dto.project.FileNode;

import java.util.List;

public interface ProjectFileService {

    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);

    void saveFile(Long projectId, String filePath, String fileContent);
}
