package com.example.domain.ports;

import com.example.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    List<User> findAllUsers();

    //mutation
    User createUser(User user);
    User updateUser(Long id, User user);
    boolean deleteUserById(Long id);

}
