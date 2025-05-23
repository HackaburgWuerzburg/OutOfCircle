package com.example.infrastructure.persistence.service;

import com.example.domain.enums.DifficultyType;
import com.example.domain.models.Challenge;
import com.example.domain.ports.ChallengeService;
import com.example.domain.ports.LLMService;
import com.example.infrastructure.mapper.ChallengeMapper;
import com.example.infrastructure.persistence.entity.ChallengeEntity;
import com.example.infrastructure.persistence.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeMapper challengeMapper;
    private final LLMService llmService;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository, ChallengeMapper challengeMapper, LLMService llmService) {
        this.challengeRepository = challengeRepository;
        this.challengeMapper = challengeMapper;
        this.llmService = llmService;
    }
    public String generateAndSaveChallenge(Long userId) {
        List<Challenge> history = challengeRepository.findByUserId(userId)
                .stream()
                .map(challengeMapper::entityToDomain)
                .collect(Collectors.toUnmodifiableList());

        String newChallengeText = llmService.generateChallengeFromPreviousOnes(history);

        // Sadece bir cümleyle bitmiyorsa nokta ekle
        if (!newChallengeText.trim().endsWith(".")) {
            newChallengeText = newChallengeText.trim() + ".";
        }

        Challenge newChallenge = new Challenge();
        newChallenge.setUserId(userId);
        newChallenge.setContent(newChallengeText);
        newChallenge.setDate(LocalDate.now());
        newChallenge.setCompleted(false); // başlangıçta tamamlanmamış
        newChallenge.setTopic("LLM Generated"); // istersen konu ekleyebilirsin
        newChallenge.setDifficulty(DifficultyType.EASY); // varsayılan bir zorluk

        challengeRepository.save(challengeMapper.domainToEntity(newChallenge));

        return newChallengeText;
    }

    @Override
    public Optional<Challenge> findChallengeById(Long id) {
        return challengeRepository.findById(id)
                .map(challengeMapper::entityToDomain);
    }

    @Override
    public List<Challenge> findChallengesByUserId(Long userId) {
        return challengeRepository.findByUserId(userId)
                .stream()
                .map(challengeMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Challenge> findChallengesByTopic(String topic) {
        return challengeRepository.findByTopic(topic)
                .stream()
                .map(challengeMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Challenge> findChallengesByDate(LocalDate date) {
        return challengeRepository.findByDate(date)
                .stream()
                .map(challengeMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Challenge> findChallengesByDifficulty(DifficultyType difficulty) {
        return challengeRepository.findByDifficulty(difficulty)
                .stream()
                .map(challengeMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Challenge> findChallengesByCompletionStatus(boolean isCompleted) {
        return challengeRepository.findByIsCompleted(isCompleted)
                .stream()
                .map(challengeMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Challenge> findAllChallenges() {
        return challengeRepository.findAll()
                .stream()
                .map(challengeMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Challenge createChallenge(Challenge challenge) {
        ChallengeEntity savedChallengeEntity = challengeRepository.save(challengeMapper.domainToEntity(challenge));
        return challengeMapper.entityToDomain(savedChallengeEntity);
    }

    @Override
    public Challenge updateChallenge(Long id, Challenge challenge) {
        ChallengeEntity updatedChallengeEntity = challengeRepository.save(challengeMapper.domainToEntity(challenge));
        updatedChallengeEntity.setId(id);
        return challengeMapper.entityToDomain(challengeRepository.save(updatedChallengeEntity));
    }

    @Override
    public boolean deleteChallenge(Long id) {
        if(challengeRepository.existsById(id)) {
            challengeRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }


}
