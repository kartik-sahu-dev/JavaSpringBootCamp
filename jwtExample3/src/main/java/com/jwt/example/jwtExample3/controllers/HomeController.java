package com.jwt.example.jwtExample3.controllers;

import com.jwt.example.jwtExample3.models.User;
import com.jwt.example.jwtExample3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("getting user");
        return this.userService.getUser();
    }

    @GetMapping("current-user")
    public String getLoggedInUser(Principal user) {
        return user.getName();
    }
}
