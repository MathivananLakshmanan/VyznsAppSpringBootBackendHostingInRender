package com.example.vyzns.Controller;

import com.example.vyzns.Dto.ChatRequest;
import com.example.vyzns.Dto.ChatResponse;
import com.example.vyzns.Service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private  final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest req){

         return ResponseEntity.ok(chatService.chat(req));
    }
}
