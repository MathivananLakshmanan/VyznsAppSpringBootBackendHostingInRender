package com.example.vyzns.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User{   

      @Id
      @GeneratedValue( strategy = GenerationType.IDENTITY )
      private Long id;

      private String name;

      private String email;

      private String password;

      private Double rating =0.0;

      private Boolean isVerified =false;

      @Column(name = "created_at")
      private LocalDateTime createAt = LocalDateTime.now();

      @Column(name = "updated_at")
      private LocalDateTime updateAt = LocalDateTime.now();


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rating = 0.0;
        this.isVerified = false;
    }
}