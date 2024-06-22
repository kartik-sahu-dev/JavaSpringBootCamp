package com.jwt.example.jwtExample3.services;

import com.jwt.example.jwtExample3.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(),"Kartik Sahu","kartik@dev.com"));
        store.add(new User(UUID.randomUUID().toString(),"Harsh Sahu","harsh@dev.com"));
        store.add(new User(UUID.randomUUID().toString(),"Ankit Sahu","ankit@dev.com"));
        store.add(new User(UUID.randomUUID().toString(),"Sonu Sahu","sonu@dev.com"));
    }

    public List<User> getUser() {
        return this.store;
    }

}
