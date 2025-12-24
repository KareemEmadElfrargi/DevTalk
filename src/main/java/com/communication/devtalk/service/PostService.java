package com.communication.devtalk.service;

import com.communication.devtalk.model.Post;
import com.communication.devtalk.model.User;
import com.communication.devtalk.repository.PostRepository;
import com.communication.devtalk.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post createPost(Long userId, String content) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        user.setPostCount(user.getPostCount() + 1);

        Post post = Post.builder()
                .content(content)
                .author(user)
                .build();

        return postRepository.save(post);
    }
}
