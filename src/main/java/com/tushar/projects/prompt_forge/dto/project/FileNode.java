package com.tushar.projects.prompt_forge.dto.project;

public record FileNode(
        String path) {

    @Override
    public String toString() {
        return path;
    }
}
