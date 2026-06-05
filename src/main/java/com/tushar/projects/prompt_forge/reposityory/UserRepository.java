package com.tushar.projects.prompt_forge.reposityory;

import com.tushar.projects.prompt_forge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
