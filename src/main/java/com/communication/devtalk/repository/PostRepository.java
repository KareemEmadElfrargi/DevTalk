package com.communication.devtalk.repository;

import com.communication.devtalk.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // This executes a JOIN instead of multiple selects
    @EntityGraph(attributePaths = {"comments", "topics"})
    List<Post> findByAuthorId(Long userId);

}
