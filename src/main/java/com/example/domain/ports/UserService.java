package com.example.domain.ports;

import com.example.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    List<User> findAllUsers();
    void updateUserTopic(Long userId, String topicStr);
    void assignTopicFromQuestionnaire(Long userId, String promptText);


    //mutation
    User createUser(User user);
    User updateUser(Long id, User user);
    boolean deleteUserById(Long id);

}
