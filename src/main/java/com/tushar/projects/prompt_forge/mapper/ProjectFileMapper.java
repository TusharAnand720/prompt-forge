package com.tushar.projects.prompt_forge.mapper;

import com.tushar.projects.prompt_forge.dto.project.FileNode;
import com.tushar.projects.prompt_forge.entity.ProjectFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectFileMapper {

    List<FileNode> toListOfFileNode(List<ProjectFile> projectFileList);

}
