package com.tushar.projects.prompt_forge.reposityory;

import com.tushar.projects.prompt_forge.entity.ChatSession;
import com.tushar.projects.prompt_forge.entity.ChatSessionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, ChatSessionId> {
}
