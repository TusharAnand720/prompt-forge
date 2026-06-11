package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.project.FileContentResponse;
import com.tushar.projects.prompt_forge.dto.project.FileNode;
import com.tushar.projects.prompt_forge.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
