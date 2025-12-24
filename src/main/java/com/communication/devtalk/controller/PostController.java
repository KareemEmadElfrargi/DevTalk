package com.communication.devtalk.controller;


import com.communication.devtalk.dto.PostRequest;
import com.communication.devtalk.dto.PostResponse;
import com.communication.devtalk.model.Post;
import com.communication.devtalk.repository.PostRepository;
import com.communication.devtalk.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository; // Direct access for Query testing (N+1)

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request) {
        Post post = postService.createPost(request.userId(), request.content());
        return new ResponseEntity<>(mapToResponse(post), HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUser(@PathVariable Long userId) {

        // Optimized fetch with @EntityGraph
        List<Post> posts = postRepository.findByAuthorId(userId);

        List<PostResponse> response = posts.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }


    private PostResponse mapToResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getContent(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getComments().size() // This would trigger Lazy Log if not for EntityGraph
        );
    }

}
