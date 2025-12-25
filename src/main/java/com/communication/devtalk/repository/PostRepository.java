package com.communication.devtalk.repository;

import com.communication.devtalk.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("SELECT p FROM Post p " +
//            "LEFT JOIN FETCH p.comments " +
//            "LEFT JOIN FETCH p.author " +
//            "LEFT JOIN FETCH p.topics")

    @EntityGraph(attributePaths = {"comments", "topics"})
    List<Post> findByAuthorId(Long userId);

}
