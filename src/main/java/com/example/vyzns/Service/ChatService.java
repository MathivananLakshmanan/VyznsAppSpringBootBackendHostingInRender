package com.example.vyzns.Service;

import com.example.vyzns.Dto.ChatRequest;
import com.example.vyzns.Dto.ChatResponse;
import com.example.vyzns.Dto.OllamaRequest;
import com.example.vyzns.Dto.OllamaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {

    private final RestTemplate restTemplate;

    @Value("${ollama.api.url}")
    private String apiUrl;

    @Value("${ollama.api.key}")
    private String apiKey;

    @Value("${ollama.api.model}")
    private String model;

    public ChatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ChatResponse chat(ChatRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        String shortPrompt = "Answer in 2-3 sentences only. Be concise.\n\n" + request.getMessage();
        OllamaRequest body = new OllamaRequest(model, shortPrompt, false);
        HttpEntity<OllamaRequest> entity = new HttpEntity<>(body, headers);

        ResponseEntity<OllamaResponse> response = restTemplate.exchange(
                apiUrl, HttpMethod.POST, entity, OllamaResponse.class);

        String reply = response.getBody() != null ? response.getBody().getResponse() : "No response";
        return ChatResponse.builder().reply(reply).build();
    }
}
