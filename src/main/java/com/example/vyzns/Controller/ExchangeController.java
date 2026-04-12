package com.example.vyzns.Controller;

import com.example.vyzns.Dto.ExchangeRequest;
import com.example.vyzns.Entity.ExchangeIteam;
import com.example.vyzns.Enum.Status;
import com.example.vyzns.Service.ExchangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    // 🔹 1. Create Exchange Request
    @PostMapping
    public ResponseEntity<ExchangeIteam> create(@RequestBody ExchangeRequest dto) {
        return ResponseEntity.ok(exchangeService.createRequest(dto));
    }

    // 🔹 2. Get User Requests
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExchangeIteam>> getUserRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(exchangeService.getUserRequests(userId));
    }

    // 🔹 3. Update Status
    @PutMapping("/{exchangeId}")
    public ResponseEntity<ExchangeIteam> updateStatus(
            @PathVariable Long exchangeId,
            @RequestParam Status status) {

        return ResponseEntity.ok(exchangeService.updateStatus(exchangeId, status));
    }

    // 🔹 4. Get By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ExchangeIteam>> getByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(exchangeService.getByStatus(status));
    }
}
