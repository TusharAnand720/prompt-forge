package com.tushar.projects.prompt_forge.entity;


import com.tushar.projects.prompt_forge.enums.MessageRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false),
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    })
    ChatSession chatSession;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    MessageRole role;

    @OneToMany(mappedBy = "chatMessages", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("sequenceOrder ASC")
    List<ChatEvent> events;

    @Column(columnDefinition = "text", nullable = false)
    String content;

//    String toolsCalls; // json array of tools called

    Integer tokenUsed = 0;

    @CreationTimestamp
    Instant createdAt;
}
