package com.finance.basicassetallocation.services;

import com.finance.basicassetallocation.models.User;
import com.finance.basicassetallocation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        System.out.println(user.getUsername());
        return userRepository.save(user);
    }
}