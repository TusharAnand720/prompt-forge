package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.entity.ChatEvent;
import com.tushar.projects.prompt_forge.entity.ChatMessage;
import com.tushar.projects.prompt_forge.entity.ChatSession;
import com.tushar.projects.prompt_forge.entity.ChatSessionId;
import com.tushar.projects.prompt_forge.entity.Project;
import com.tushar.projects.prompt_forge.entity.User;
import com.tushar.projects.prompt_forge.enums.ChatEventType;
import com.tushar.projects.prompt_forge.enums.MessageRole;
import com.tushar.projects.prompt_forge.error.ResourceNotFoundException;
import com.tushar.projects.prompt_forge.llm.LLMResponseParser;
import com.tushar.projects.prompt_forge.llm.PromptUtils;
import com.tushar.projects.prompt_forge.llm.advisors.FileTreeContextAdvisor;
import com.tushar.projects.prompt_forge.llm.tools.CodeGenerationTools;
import com.tushar.projects.prompt_forge.reposityory.ChatEventRepository;
import com.tushar.projects.prompt_forge.reposityory.ChatMessageRepository;
import com.tushar.projects.prompt_forge.reposityory.ChatSessionRepository;
import com.tushar.projects.prompt_forge.reposityory.ProjectRepository;
import com.tushar.projects.prompt_forge.reposityory.UserRepository;
import com.tushar.projects.prompt_forge.security.AuthUtil;
import com.tushar.projects.prompt_forge.service.AiGenerationService;
import com.tushar.projects.prompt_forge.service.ProjectFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiGenerationServiceImpl implements AiGenerationService {

    private final ChatClient chatClient;
    private final AuthUtil authUtil;

    private final ProjectFileService projectFileService;

    private final FileTreeContextAdvisor fileTreeContextAdvisor;

    private final LLMResponseParser llmResponseParser;

    private final ChatSessionRepository chatSessionRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatEventRepository chatEventRepository;

    @Override
//    @PreAuthorize("@security.canEditProject(#projectId)")
    public Flux<String> streamResponse(String userMessage, Long projectId) {
        try {
            Long userId = authUtil.getCurrentUserId();

            ChatSession chatSession = createChatSessionIfNotExists(projectId, userId);

            Map<String, Object> advisorParams = Map.of(
                    "userId", userId,
                    "projectId", projectId
            );

            StringBuilder fullResponseBuffer = new StringBuilder();

            CodeGenerationTools codeGenerationTools = new CodeGenerationTools(projectFileService, projectId);

            return chatClient.prompt()
                    .system(PromptUtils.CODE_GENERATION_SYSTEM_PROMPT)
                    .user(userMessage)
                    .tools(codeGenerationTools)
                    .advisors(advisorSpec -> {
                        advisorSpec.params(advisorParams);
                        advisorSpec.advisors(fileTreeContextAdvisor);
                    })
                    .stream()
                    .chatResponse()
                    .doOnNext(response -> {
                        String content = response.getResult().getOutput().getText();
                        if (content != null) {
                            fullResponseBuffer.append(content);
                        }
                    })
                    .doOnComplete(() -> {
                        CompletableFuture.runAsync(() ->
                                finalizeChats(userMessage, chatSession, fullResponseBuffer.toString(), projectId));
                    })
                    .doOnError(error -> log.error("Error during streaming for project : {}", projectId + " , error : " + error.getMessage()))
                    .map(response -> Objects.requireNonNull(response.getResult().getOutput().getText()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ChatSession createChatSessionIfNotExists(Long projectId, Long userId) {
        ChatSessionId chatSessionId = new ChatSessionId(projectId, userId);
        ChatSession chatSession = chatSessionRepository.findById(chatSessionId).orElse(null);
        if (chatSession == null) {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFoundException("project", projectId.toString()));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("user", userId.toString()));

            chatSession = ChatSession.builder()
                    .id(chatSessionId)
                    .project(project)
                    .user(user)
                    .build();

            chatSession = chatSessionRepository.save(chatSession);
        }
        return chatSession;
    }

    private void finalizeChats(String userPrompt, ChatSession chatSession, String fullText, Long projectId) {

        // saving user message
        ChatMessage userChatMessage = ChatMessage.builder()
                .chatSession(chatSession)
                .role(MessageRole.USER)
                .content(userPrompt)
                .build();
        chatMessageRepository.save(userChatMessage);

        // saving LLM message
        ChatMessage assistanceChatMessage = ChatMessage.builder()
                .chatSession(chatSession)
                .role(MessageRole.ASSISTANT)
                .build();

        List<ChatEvent> chatEventList = llmResponseParser.parseChatEvents(fullText, assistanceChatMessage);

        chatEventList.stream()
                .filter(event -> event.getType() == ChatEventType.FILE_EDIT)
                .forEach(event -> projectFileService.saveFile(projectId, event.getFilePath(), event.getContent()));

        chatEventRepository.saveAll(chatEventList);
    }
}
