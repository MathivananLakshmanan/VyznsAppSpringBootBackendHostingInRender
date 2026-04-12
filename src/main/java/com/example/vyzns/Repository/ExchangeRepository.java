package com.example.vyzns.Repository;

import com.example.vyzns.Entity.ExchangeIteam;
import com.example.vyzns.Entity.Items;
import com.example.vyzns.Entity.User;
import com.example.vyzns.Enum.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeIteam, Long> {

    // ✅ correct
    List<ExchangeIteam> findByFromUser(User fromUser);

    List<ExchangeIteam> findByToUser(User toUser);

    List<ExchangeIteam> findByFromUserOrToUser(User fromUser, User toUser);

    // ✅ correct
    List<ExchangeIteam> findByStatus(Status status);

    // ✅ correct FULL match
    Optional<ExchangeIteam> findByFromUserAndOfferedItemAndRequestedItem(
            User fromUser,
            Items offeredItem,
            Items requestedItem
    );

    // ✅ correct
    List<ExchangeIteam> findByStatusIn(List<Status> statuses);


}