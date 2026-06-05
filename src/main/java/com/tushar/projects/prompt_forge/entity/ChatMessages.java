package com.tushar.projects.prompt_forge.entity;


import com.tushar.projects.prompt_forge.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessages {

    Long id;

    ChatSession chatSession;

    String content;
    String toolsCalls; // json array of tools called
    Integer tokenUsed;

    MessageRole role;

    Instant createdAt;


}
