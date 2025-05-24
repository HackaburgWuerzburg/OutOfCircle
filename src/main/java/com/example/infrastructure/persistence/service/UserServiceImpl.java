package com.example.infrastructure.persistence.service;

import com.example.domain.enums.TopicType;
import com.example.domain.models.User;
import com.example.domain.ports.UserService;
import com.example.infrastructure.mapper.UserMapper;
import com.example.infrastructure.persistence.entity.UserEntity;
import com.example.infrastructure.persistence.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final LLMServiceImpl llmService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, LLMServiceImpl llmService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.llmService = llmService;
    }

    @Override
    public void assignTopicFromQuestionnaire(Long userId, String promptText) {
        String topicStr = llmService.detectTopicFromQuestionnaire(promptText);

        TopicType topicEnum;
        try {
            topicEnum = TopicType.valueOf(topicStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid topic from LLM: " + topicStr);
        }

        Optional<User> user = userRepository.findById(userId).map(userMapper::entityToDomain);
        user.get().setTopic(topicEnum);
        UserEntity savedUserEntity = userRepository.save(userMapper.domainToEntity(user.orElse(null)));
        userMapper.entityToDomain(savedUserEntity);
    }


    @Override
    public void updateUserTopic(Long userId, String topicStr) {
        TopicType topicEnum = TopicType.valueOf(topicStr.toUpperCase());
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setTopic(topicEnum);
        userRepository.save(user);
    }


    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::entityToDomain);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::entityToDomain);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::entityToDomain);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(User user) {
        UserEntity savedUserEntity = userRepository.save(userMapper.domainToEntity(user));
        return userMapper.entityToDomain(savedUserEntity);
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity updatedUserEntity = userMapper.domainToEntity(user);
        updatedUserEntity.setId(id);
        return userMapper.entityToDomain(userRepository.save(updatedUserEntity));
    }

    @Override
    public boolean deleteUserById(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // her gece 00:00'da çalışır
    public void resetDailyUserStats() {
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity user : users) {
            user.setSkipCountToday(0);
            user.setRewardGivenToday(false);
        }
        userRepository.saveAll(users);
        System.out.println("✅ Daily user stats reset.");
    }
}
