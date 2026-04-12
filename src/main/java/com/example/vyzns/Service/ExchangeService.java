package com.example.vyzns.Service;

import com.example.vyzns.Dto.ExchangeRequest;
import com.example.vyzns.Entity.ExchangeIteam;
import com.example.vyzns.Entity.Items;
import com.example.vyzns.Entity.User;
import com.example.vyzns.Enum.Status;
import com.example.vyzns.Repository.ExchangeRepository;
import com.example.vyzns.Repository.IteamRepository;
import com.example.vyzns.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private IteamRepository itemRepo;

    // 🔹 1. CREATE EXCHANGE REQUEST
    public ExchangeIteam createRequest(ExchangeRequest dto) {

        User requester = userRepo.findById(dto.getFromUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User toUser = userRepo.findById(dto.getToUserId())
                .orElseThrow(() -> new RuntimeException("Target user not found"));

        Items offeredItem = itemRepo.findById(dto.getOfferedItemId())
                .orElseThrow(() -> new RuntimeException("Offered item not found"));

        Items requestedItem = itemRepo.findById(dto.getRequestedItemId())
                .orElseThrow(() -> new RuntimeException("Requested item not found"));

        // 🔥 Duplicate check
        exchangeRepo.findByFromUserAndOfferedItemAndRequestedItem(
                requester, offeredItem, requestedItem
        ).ifPresent(e -> {
            throw new RuntimeException("Exchange already exists");
        });

        ExchangeIteam exchange = new ExchangeIteam();
        exchange.setFromUser(requester);
        exchange.setToUser(toUser);
        exchange.setOfferedItem(offeredItem);
        exchange.setRequestedItem(requestedItem);
        exchange.setStatus(Status.PENDING);
        exchange.setMessage(dto.getMessage());

        return exchangeRepo.save(exchange);
    }

    // 🔹 2. GET ALL REQUESTS BY USER ....CHECk
    public List<ExchangeIteam> getUserRequests(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return exchangeRepo.findByFromUserOrToUser(user, user);
    }

    // 🔹 3. UPDATE STATUS (ACCEPT / REJECT)
    public ExchangeIteam updateStatus(Long exchangeId, Status status) {

        ExchangeIteam exchange = exchangeRepo.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found"));

        exchange.setStatus(status);

        return exchangeRepo.save(exchange);
    }

    // 🔹 4. GET BY STATUS
    public List<ExchangeIteam> getByStatus(Status status) {
        return exchangeRepo.findByStatus(status);
    }

    
}






















