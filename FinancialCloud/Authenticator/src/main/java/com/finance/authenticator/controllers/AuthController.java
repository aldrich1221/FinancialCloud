package com.finance.authenticator.controllers;

import com.finance.authenticator.Models.User;
import com.finance.authenticator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return response;
    }

//    @PostMapping("/login")
//    public Map<String, String> login(@RequestBody Map<String, String> user) {
//        String username = user.get("username");
//        String password = user.get("password");
//
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        Map<String, String> response = new HashMap<>();
//        response.put("jwt", jwt);
//        return response;
//    }
        @PostMapping("/api/v1/login")
        public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            if(authentication.isAuthenticated()){
                return JwtResponseDTO.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()).build();
            } else {
                throw new UsernameNotFoundException("invalid user request..!!");
            }
        }


}