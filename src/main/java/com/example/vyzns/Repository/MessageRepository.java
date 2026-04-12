package com.example.vyzns.Repository;

import com.example.vyzns.Dto.ExchangeRequest;
import com.example.vyzns.Entity.ExchangeIteam;
import com.example.vyzns.Entity.Message;
import com.example.vyzns.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message , Long> {


    List<Message> findByExchangeOrderByCreatedAtAsc(ExchangeIteam exchange);

    List<Message> findByExchangeAndSenderNotAndReadFalse(
            ExchangeIteam exchange,
            User sender
    );

    long countByExchangeAndSenderNotAndReadFalse(
            ExchangeIteam exchange,
            User sender
    );


}
