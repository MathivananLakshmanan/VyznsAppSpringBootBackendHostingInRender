package com.example.vyzns.Service;

import com.example.vyzns.Dto.MessageReq;
import com.example.vyzns.Entity.ExchangeIteam;
import com.example.vyzns.Entity.Message;
import com.example.vyzns.Entity.User;
import com.example.vyzns.Repository.MessageRepository;
import com.example.vyzns.Repository.ExchangeRepository;
import com.example.vyzns.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private UserRepository userRepo;

    //  1. SEND MESSAGE
    public Message sendMessage(MessageReq req) {

        //  get exchange
        ExchangeIteam exchange = exchangeRepo.findById(req.getExchangeId())
                .orElseThrow(() -> new RuntimeException("Exchange not found"));

        //  get sender
        User sender = userRepo.findById(req.getSenderId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  SECURITY CHECK — only fromUser or toUser can message
        if (!exchange.getFromUser().getId().equals(sender.getId()) &&
            !exchange.getToUser().getId().equals(sender.getId())) {
            throw new RuntimeException("Unauthorized user");
        }

        //  create message
        Message msg = new Message();
        msg.setExchange(exchange);
        msg.setSender(sender);
        msg.setMessage(req.getMessage());
        msg.setRead(false);

        return messageRepo.save(msg);
    }

    // 🔹 2. GET CHAT HISTORY
    public List<Message> getChat(Long exchangeId) {

        ExchangeIteam exchange = exchangeRepo.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found"));

        return messageRepo.findByExchangeOrderByCreatedAtAsc(exchange);
    }

    // 🔹 3. MARK AS READ
    public void markAsRead(Long exchangeId, Long userId) {

        ExchangeIteam exchange = exchangeRepo.findById(exchangeId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();

        List<Message> unread =
                messageRepo.findByExchangeAndSenderNotAndReadFalse(exchange, user);

        unread.forEach(msg -> msg.setRead(true));

        messageRepo.saveAll(unread);
    }

    // 🔹 4. UNREAD COUNT
    public long getUnreadCount(Long exchangeId, Long userId) {

        ExchangeIteam exchange = exchangeRepo.findById(exchangeId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();

        return messageRepo.countByExchangeAndSenderNotAndReadFalse(exchange, user);
    }
}
