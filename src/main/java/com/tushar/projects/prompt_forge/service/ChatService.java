package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.chat.ChatResponse;

import java.util.List;

public interface ChatService {

    List<ChatResponse> getProjectChatHistory(Long projectId);

}
