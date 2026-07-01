package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.llm.PromptUtils;
import com.tushar.projects.prompt_forge.llm.advisors.FileTreeContextAdvisor;
import com.tushar.projects.prompt_forge.security.AuthUtil;
import com.tushar.projects.prompt_forge.service.AiGenerationService;
import com.tushar.projects.prompt_forge.service.ProjectFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiGenerationServiceImpl implements AiGenerationService {

    private static final Pattern FILE_TAG_PATTER = Pattern.compile("<file path=\"([^\"]+)\">(.*?)</file>", Pattern.DOTALL);
    private final ChatClient chatClient;
    private final AuthUtil authUtil;

    private final ProjectFileService projectFileService;

    private final FileTreeContextAdvisor fileTreeContextAdvisor;

    @Override
//    @PreAuthorize("@security.canEditProject(#projectId)")
    public Flux<String> streamResponse(String userMessage, Long projectId) {
        try {
            Long userId = authUtil.getCurrentUserId();

            createChatSessionIfNotExists(projectId, userId);

            Map<String, Object> advisorParams = Map.of(
                    "userId", userId,
                    "projectId", projectId
            );

            StringBuilder fullResponseBuffer = new StringBuilder();

            return chatClient.prompt()
                    .system(PromptUtils.CODE_GENERATION_SYSTEM_PROMPT)
                    .user(userMessage)
                    .advisors(advisorSpec -> {
                        advisorSpec.params(advisorParams);
                        advisorSpec.advisors(fileTreeContextAdvisor);
                    })
                    .stream()
                    .chatResponse()
                    .doOnNext(response -> {
                        String content = Objects.requireNonNull(response.getResult()).getOutput().getText();
                        fullResponseBuffer.append(content);
                    })
                    .doOnComplete(() -> {
                        Schedulers.boundedElastic().schedule(() -> parseAndSaveFiles(fullResponseBuffer.toString(), projectId));
                    })
                    .doOnError(error -> log.error("Error during streaming for project : {}", projectId + " , error : " + error.getMessage()))
                    .map(response -> Objects.requireNonNull(response.getResult()).getOutput().getText());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createChatSessionIfNotExists(Long projectId, Long userId) {

    }

    private void parseAndSaveFiles(String fullResponse, Long projectId) {

        Matcher matcher = FILE_TAG_PATTER.matcher(fullResponse);

        while (matcher.find()) {
            String filePath = matcher.group(1);
            String fileContent = matcher.group(0).trim();

            projectFileService.saveFile(projectId, filePath, fileContent);
        }
    }
}
