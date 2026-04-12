package com.example.vyzns.Controller;

import com.example.vyzns.Dto.UserRequest;
import com.example.vyzns.Dto.UserResponse;
import com.example.vyzns.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

       private  final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }

      @PostMapping
    public UserResponse creatrUser(@RequestBody UserRequest request){
          return  service.createUser(request);
      }
}
