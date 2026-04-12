package com.example.vyzns.Entity;


import com.example.vyzns.Enum.Condition;
import com.example.vyzns.Enum.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name = "items")
public class Items {


     @Id
     @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;

     private String title;

    @Column(nullable = false, length = 100)
     private String description;

    @Column(columnDefinition = "TEXT")
    private String  image_url;

     @ManyToOne
     @JoinColumn(name = "owner_id" )
     private User owner;

     private String  category;

     @Enumerated(EnumType.STRING)
     @Column(name = "`condition`")
     private Condition condition = Condition.NEW;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
     private ProductStatus status = ProductStatus.AVAILABLE;

    @Column( precision = 10 , scale = 2)
    @DecimalMin("0.00")
    private BigDecimal price;


    @Column(name="created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;




}
