package com.finance.authentication.controllers;


import com.finance.authentication.models.AuthRequestDTO;
import com.finance.authentication.models.JwtResponseDTO;
import com.finance.authentication.models.UserRequest;
import com.finance.authentication.models.UserResponse;
import com.finance.authentication.services.JWTService;
import com.finance.authentication.services.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    MyUserDetailsService userService;

    @Autowired
    private JWTService jwtService;

    @PreAuthorize("hasAnyRole('ROLE_GENERALUSER', 'ADMIN')")
    @GetMapping("/ping")
    public String ping() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/test")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private  AuthenticationManager authenticationManager;

    @PostMapping(value = "/save")
    public ResponseEntity saveUser(@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.saveUser(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

        if(authentication.isAuthenticated()){
            JwtResponseDTO.JwtResponseDTOBuilder builder=JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()));
            System.out.println(builder);

            return builder
                    .build();

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }

    }





}