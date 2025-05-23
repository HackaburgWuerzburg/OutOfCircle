package com.example.infrastructure.persistence.service;

import com.example.domain.enums.DifficultyType;
import com.example.domain.models.Challenge;
import com.example.domain.models.Journal;
import com.example.domain.ports.ChallengeService;
import com.example.domain.ports.LLMService;
import com.example.infrastructure.mapper.ChallengeMapper;
import com.example.infrastructure.mapper.JournalMapper;
import com.example.infrastructure.persistence.entity.ChallengeEntity;
import com.example.infrastructure.persistence.entity.UserEntity;
import com.example.infrastructure.persistence.repository.ChallengeRepository;
import com.example.infrastructure.persistence.repository.JournalRepository;
import com.example.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final JournalRepository journalRepository;
    private final ChallengeMapper challengeMapper;
    private final JournalMapper journalMapper;
    private final LLMService llmService;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository, UserRepository userRepository, JournalRepository journalRepository, ChallengeMapper challengeMapper, JournalMapper journalMapper, LLMService llmService) {
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
        this.journalRepository = journalRepository;
        this.challengeMapper = challengeMapper;
        this.journalMapper = journalMapper;
        this.llmService = llmService;
    }

    public String generateAndSaveChallenge(Long userId) {
        // 1. Kullanıcının zorluk seviyesi
        DifficultyType difficulty = userRepository.findById(userId).get().getDifficulty();

        // 2. Son journal yazısını al
        Journal latestJournal = journalRepository.findLatestJournalByUserId(userId)
                .map(journalMapper::entityToDomain)
                .orElseThrow(() -> new RuntimeException("No journal found for user"));

        String journalText = latestJournal.getContent();

        // 3. Journal'dan topic tahmin et
        String topic = llmService.detectTopicFromJournal(journalText);

        // 4. Topic'teki önceki challenge'ları topla
        List<Challenge> topicHistory = challengeRepository.findByTopic(topic)
                .stream()
                .map(challengeMapper::entityToDomain)
                .collect(Collectors.toList());

        // 5. LLM'den yeni challenge iste
        String newChallengeText = llmService.generateChallengeFromTopicAndDifficulty(topicHistory, topic, difficulty);

        if (!newChallengeText.trim().endsWith(".")) {
            newChallengeText = newChallengeText.trim() + ".";
        }

        // 6. Challenge'ı oluştur ve kaydet
        Challenge newChallenge = new Challenge();
        newChallenge.setUserId(userId);
        newChallenge.setTopic(topic);
        newChallenge.setContent(newChallengeText);
        newChallenge.setDate(LocalDate.now());
        newChallenge.setDifficulty(difficulty);
        newChallenge.setCompleted(false);

        challengeRepository.save(challengeMapper.domainToEntity(newChallenge));

        return newChallengeText;
    }

    public Challenge skipChallenge(Long userId, Long challengeId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getSkipCountToday() >= 2) {
            throw new RuntimeException("Daily skip limit reached");
        }

        // Zorluk düşür (örnek)
        DifficultyType current = user.getDifficulty();
        if (current == DifficultyType.HARD) user.setDifficulty(DifficultyType.MEDIUM);
        else if (current == DifficultyType.MEDIUM) user.setDifficulty(DifficultyType.EASY);

        user.setSkipCountToday(user.getSkipCountToday() + 1);
        userRepository.save(user);

        // İlgili Challenge güncellenebilir veya loglanabilir
        return challengeRepository.findById(challengeId)
                .map(challengeMapper::entityToDomain)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));
    }

    public Challenge completeChallenge(Long userId, Long challengeId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ChallengeEntity challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        if (!user.isRewardGivenToday()) {
            user.setCoin(user.getCoin() + 1);
            user.setRewardGivenToday(true);
            userRepository.save(user);
        }

        challenge.setCompleted(true);
        return challengeMapper.entityToDomain(challengeRepository.save(challenge));
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
