package com.example.infrastructure.persistence.service;

import com.example.domain.ports.LLMService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    @Override
    public String generateDailyChallenge(String userPattern) {
        try {
            String escapedUserPattern = escapeJson(userPattern);

            String requestBody = """
            {
                "model": "deepseek-chat",
                "messages": [{
                    "role": "user",
                    "content": "The user has the following patterns:\\n%s\\nSuggest one short daily goal to break this comfort zone."
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

            if (!root.has("choices")) {
                return "Response missing 'choices'";
            }

            JsonNode firstChoice = root.path("choices").get(0);
            if (firstChoice == null || !firstChoice.has("message")) {
                return "Response missing 'message'";
            }

            JsonNode message = firstChoice.path("message");
            if (!message.has("content")) {
                return "Response missing 'content'";
            }

            return message.get("content").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, failed to parse the challenge.";
        }
    }
}
