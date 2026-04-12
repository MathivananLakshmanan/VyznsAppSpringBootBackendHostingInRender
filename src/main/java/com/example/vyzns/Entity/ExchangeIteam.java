package com.example.vyzns.Entity;


import com.example.vyzns.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="exchange_requests")
public class ExchangeIteam {

      @Id
     @GeneratedValue( strategy = GenerationType.IDENTITY  )
      private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "offered_item_id")
    private Items offeredItem;

    @ManyToOne
    @JoinColumn(name = "requested_item_id")
    private Items requestedItem;

       @Enumerated(EnumType.STRING)
       private Status status =Status.PENDING;

       private String message;

       private LocalDateTime createdAt = LocalDateTime.now();
}
