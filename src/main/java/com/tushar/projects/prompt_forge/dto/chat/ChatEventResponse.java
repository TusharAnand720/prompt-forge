package com.tushar.projects.prompt_forge.dto.chat;

import com.tushar.projects.prompt_forge.enums.ChatEventType;

public record ChatEventResponse(
        Long id,
        ChatEventType type,
        Integer sequenceOrder,
        String content,
        String filePath,
        String metadata) {
}
