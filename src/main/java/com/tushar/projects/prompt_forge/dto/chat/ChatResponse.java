package com.tushar.projects.prompt_forge.dto.chat;

import com.tushar.projects.prompt_forge.entity.ChatEvent;
import com.tushar.projects.prompt_forge.entity.ChatSession;

import com.tushar.projects.prompt_forge.enums.MessageRole;

import java.time.Instant;
import java.util.List;

public record ChatResponse(
        Long id,
        ChatSession chatSession,
        MessageRole role,
        List<ChatEvent> events,
        String content,
        Integer tokenUsed ,
        Instant createdAt) {
}
