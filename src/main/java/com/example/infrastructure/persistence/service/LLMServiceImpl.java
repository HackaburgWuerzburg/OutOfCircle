package com.example.infrastructure.persistence.service;

import com.example.domain.enums.DifficultyType;
import com.example.domain.models.Challenge;
import com.example.domain.ports.LLMService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LLMServiceImpl implements LLMService {
    private final String apiKey = "sk-0fc837e1c70242399c7b276e26c6c64b";
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";

    @PostConstruct
    public void testKey() {
        System.out.println("API KEY: " + apiKey);
    }

    private String escapeJson(String input) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(input).replaceAll("^\"|\"$", "");
        } catch (Exception e) {
            return input;
        }
    }

    public String generateChallengeFromPreviousOnes(List<Challenge> pastChallenges) {
        String combined = pastChallenges.stream()
                .map(Challenge::getContent)
                .collect(Collectors.joining("\n"));

        String prompt = "Here are the user's previous daily challenges:\n" + combined +
                "\n\nBased on the style of these, generate a new daily challenge. Respond with exactly ONE short sentence, nothing else.";

        return generateDailyChallenge(prompt);
    }

    @Override
    public String detectTopicFromJournal(String journalText) {
        String prompt = """
        The user wrote the following journal entry:

        "%s"

        Decide which topic this journal fits best under, choosing only from the following: 
        fitness, productivity, mindfulness, social, nutrition, learning.

        Respond with only the topic word.
        """.formatted(journalText);

        String result = generateDailyChallenge(prompt);
        return result.trim().toLowerCase(); // örn: "fitness"
    }

    @Override
    public String detectTopicFromQuestionnaire(String promptText) {
        String prompt = """
    Based on the user's answers:

    %s

    Choose the single most relevant topic from the following list: 
    fitness, productivity, mindfulness, social, nutrition, learning.

    Respond only with the topic word.
    """.formatted(promptText);

        String result = generateDailyChallenge(prompt);
        return result.trim().toLowerCase(); // örn: "mindfulness"
    }


    @Override
    public String generateChallengeFromTopicAndDifficulty(List<Challenge> challenges, String topic, DifficultyType difficulty) {
        String combined = challenges.stream()
                .map(Challenge::getContent)
                .collect(Collectors.joining("\n"));

        String prompt = """
        You are generating a new daily challenge in the topic "%s" with difficulty "%s".
        Here are previous challenges in this topic:
        %s

        Based on these, suggest ONE new daily challenge. Respond with exactly one short sentence and nothing else.
        """.formatted(topic, difficulty.name(), combined);

        return generateDailyChallenge(prompt);
    }


    @Override
    public String generateDailyChallenge(String userPattern) {
        try {
            String escapedUserPattern = escapeJson(userPattern);

            String requestBody = """
            {
                "model": "deepseek-chat",
                "messages": [{
                    "role": "user",
                    "content": "%s"
                }],
                "temperature": 0.7
            }
            """.formatted(escapedUserPattern);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("LLM Raw Response: " + response.body());

            return extractChallenge(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, we couldn't generate your challenge today.";
        }
    }

    private String extractChallenge(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);

            JsonNode choices = root.path("choices");
            if (!choices.isArray() || choices.isEmpty()) {
                return "Response missing or empty 'choices'";
            }

            JsonNode firstChoice = choices.get(0);
            JsonNode message = firstChoice.path("message");

            if (message.has("content")) {
                String raw = message.get("content").asText();

                // Temizlik: baştaki ve sondaki tırnakları ve nokta+tırnağı sil
                String cleaned = raw.trim()
                        .replaceAll("^\"+", "")         // baştaki " sil
                        .replaceAll("\"+\\.*$", "")     // sondaki ". sil
                        .trim();

                return cleaned;
            }

            return "Response missing 'message.content'";
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, failed to parse the challenge.";
        }
    }
}
