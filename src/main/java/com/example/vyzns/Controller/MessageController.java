package com.example.vyzns.Controller;

import com.example.vyzns.Dto.MessageReq;
import com.example.vyzns.Entity.Message;
import com.example.vyzns.Service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // 🔹 1. Send Message
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody MessageReq req) {
        Message message = messageService.sendMessage(req);
        return ResponseEntity.ok(message);
    }

    // 🔹 2. Get Chat History
    @GetMapping("/{exchangeId}")
    public ResponseEntity<List<Message>> getChat(@PathVariable Long exchangeId) {
        List<Message> messages = messageService.getChat(exchangeId);
        return ResponseEntity.ok(messages);
    }

    // 🔹 3. Mark Messages as Read
    @PostMapping("/read/{exchangeId}/{userId}")
    public ResponseEntity<String> markAsRead(
            @PathVariable Long exchangeId,
            @PathVariable Long userId) {

        messageService.markAsRead(exchangeId, userId);
        return ResponseEntity.ok("Messages marked as read");
    }

    // 🔹 4. Get Unread Count
    @GetMapping("/unread/{exchangeId}/{userId}")
    public ResponseEntity<Long> getUnreadCount(
            @PathVariable Long exchangeId,
            @PathVariable Long userId) {

        long count = messageService.getUnreadCount(exchangeId, userId);
        return ResponseEntity.ok(count);
    }
}