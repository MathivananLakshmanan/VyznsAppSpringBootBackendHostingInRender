package com.example.vyzns.Controller;

import com.example.vyzns.Dto.MessageReq;
import com.example.vyzns.Entity.Message;
import com.example.vyzns.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message send(MessageReq req) {
        return messageService.sendMessage(req);
    }
}
