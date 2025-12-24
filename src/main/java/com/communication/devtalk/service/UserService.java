package com.communication.devtalk.service;

import com.communication.devtalk.model.User;
import com.communication.devtalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    @Transactional
    public User registerUser(String username, String email, Map<String, Object> settings){
        User user = User.builder()
                .username(username)
                .email(email)
                .setting(settings)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByTheme(String theme) {
        return userRepository.findByThemeSetting(theme);
    }

}
