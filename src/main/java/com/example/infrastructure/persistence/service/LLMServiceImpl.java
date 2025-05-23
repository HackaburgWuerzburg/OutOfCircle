package com.example.infrastructure.persistence.service;
import com.example.domain.ports.LLMService;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class LLMServiceImpl implements LLMService {
    private static final String API_KEY = "sk-063b3692e0d04046a584ccb2626fe63c"; // Replace with your real key
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions"; // or OpenAI/others

    @Override
    public String generateDailyChallenge(String userPattern) {
        try {
            String requestBody = """
                {
                    "model": "deepseek-chat",
                    "messages": [{
                        "role": "user",
                        "content": "The user has the following patterns:\\n%s\\nSuggest one short daily goal to break this comfort zone."
                    }],
                    "temperature": 0.7
                }
                """.formatted(userPattern);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, we couldn't generate your challenge today.";
        }
    }
}
