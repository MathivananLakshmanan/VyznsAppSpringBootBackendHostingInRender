package com.example.vyzns.Controller;


import com.example.vyzns.Config.JwtUtil;
import com.example.vyzns.Dto.*;
import com.example.vyzns.Entity.User;
import com.example.vyzns.Repository.UserRepository;
import com.example.vyzns.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest req){
        return  ResponseEntity.ok(userService.createUser(req));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){

         return  ResponseEntity.ok(userService.login(req));
    }

}
