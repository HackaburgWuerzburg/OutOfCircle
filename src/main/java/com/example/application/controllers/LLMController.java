package com.example.application.controllers;

import com.example.domain.ports.LLMService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class LLMController {
    private final LLMService llmService;

    public LLMController(LLMService llmService) {
        this.llmService = llmService;
    }

    @PostMapping("/challenge")
    public String getChallenge(@RequestBody String userPattern) {
        return llmService.generateDailyChallenge(userPattern);
    }
}
