package com.tushar.projects.prompt_forge.mapper;

import com.tushar.projects.prompt_forge.dto.chat.ChatResponse;
import com.tushar.projects.prompt_forge.entity.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    List<ChatResponse> fromListOfChatMessage(List<ChatMessage> chatMessageList);
}
