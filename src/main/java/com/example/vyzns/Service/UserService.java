package com.example.vyzns.Service;

import com.example.vyzns.Config.JwtFilter;
import com.example.vyzns.Config.JwtUtil;
import com.example.vyzns.Dto.LoginRequest;
import com.example.vyzns.Dto.LoginResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vyzns.Dto.UserRequest;
import com.example.vyzns.Dto.UserResponse;
import com.example.vyzns.Entity.User;
import com.example.vyzns.Repository.UserRepository;

@Service
public class UserService {


    private  final UserRepository repo;
    private  final PasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;
    private  final  JwtUtil jwtUtil;


    public UserService(UserRepository repo, PasswordEncoder passwordEncoder, JwtFilter jwtFilter, JwtUtil jwtUtil) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;
        this.jwtUtil = jwtUtil;
    }


    public UserResponse createUser(UserRequest  req){

        repo.findByEmail(req.getEmail()).ifPresent( u ->{
             throw  new RuntimeException("Email Allready Exists");
        } );

        User user = new User(
                req.getName(),
                req.getEmail(),
                passwordEncoder.encode(req.getPassword())
        );

        User  saved =repo.save(user);

         return  UserResponse.builder()
                 .id(saved.getId())
                 .name(saved.getName())
                 .email(saved.getEmail())
                 .rating(saved.getRating())
                 .isVerified(saved.getIsVerified())
                 .build();
    }


    public LoginResponse login(LoginRequest  req){

        User user = repo.findByEmail(req.getEmail())
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        if(!passwordEncoder.matches(req.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

            String token = JwtUtil.generateToken(user.getEmail());

         return  LoginResponse.builder()
                 .token(token)
                 .id(user.getId())
                 .name(user.getName())
                 .email(user.getEmail())
                 .build();

    }
}
