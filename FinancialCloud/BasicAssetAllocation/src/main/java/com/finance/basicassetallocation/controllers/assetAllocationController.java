package com.finance.basicassetallocation.controllers;

import com.finance.basicassetallocation.models.User;
import com.finance.basicassetallocation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class assetAllocationController {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users=userService.findAllUsers();
        System.out.println(users);
        return  users;
    }

    @PostMapping(path="/users",consumes = "application/json",produces = "application/json")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getUsername());
        return userService.saveUser(user);
    }


}
