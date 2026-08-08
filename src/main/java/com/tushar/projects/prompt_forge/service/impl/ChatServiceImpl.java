package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.chat.ChatResponse;
import com.tushar.projects.prompt_forge.entity.ChatMessage;
import com.tushar.projects.prompt_forge.entity.ChatSession;
import com.tushar.projects.prompt_forge.entity.ChatSessionId;
import com.tushar.projects.prompt_forge.mapper.ChatMapper;
import com.tushar.projects.prompt_forge.reposityory.ChatMessageRepository;
import com.tushar.projects.prompt_forge.reposityory.ChatSessionRepository;
import com.tushar.projects.prompt_forge.security.AuthUtil;
import com.tushar.projects.prompt_forge.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatSessionRepository chatSessionRepository;

    private final AuthUtil authUtil;

    private final ChatMapper chatMapper;

    @Override
    public List<ChatResponse> getProjectChatHistory(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        ChatSession chatSession = chatSessionRepository.getReferenceById(
                new ChatSessionId(projectId, userId)
        );

        List<ChatMessage> chatMessageList = chatMessageRepository.findByChatChatSession(chatSession);

        return chatMapper.fromListOfChatMessage(chatMessageList);
    }
}
