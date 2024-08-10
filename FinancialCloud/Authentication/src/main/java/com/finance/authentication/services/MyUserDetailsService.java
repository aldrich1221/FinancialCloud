package com.finance.authentication.services;

import com.finance.authentication.models.Role;
import com.finance.authentication.models.User;
import com.finance.authentication.models.UserRequest;
import com.finance.authentication.models.UserResponse;
import com.finance.authentication.repositories.RoleRepository;
import com.finance.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .collect(Collectors.toList()));
    }


    public UserResponse saveUser(UserRequest userRequest) {
        if(userRequest.getUsername() == null){
            throw new RuntimeException("Parameter username is not found in request..!!");
        } else if(userRequest.getPassword() == null){
            throw new RuntimeException("Parameter password is not found in request..!!");
        }


        User savedUser = null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = userRequest.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(encodedPassword);

        User oldUser=null;
        if(userRequest.getId() != null){
            oldUser = userRepository.findFirstById(userRequest.getId());

        } else if (userRequest.getUsername() != null) {
            oldUser =userRepository.findByUsername(userRequest.getUsername());

        }

        if(oldUser!=null){
            savedUser =updateOldUser(user,oldUser);
        }
        else {

            savedUser =createNewUser( user);


        }

        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        return userResponse;
    }

    @Transactional
    public User saveUserWithRoles(User user, Set<String> roleNames) {
        Set<Role> roles = new HashSet<>();

        for (String roleName : roleNames) {
            Role role = roleRepository.findByRolename(roleName).orElseGet(() -> {
                Role newRole = new Role();
                newRole.setRolename(roleName);
                return roleRepository.save(newRole);
            });
            roles.add(role);
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    public User updateOldUser(User user,User oldUser ) {


        if(oldUser != null){

            oldUser.setPassword(user.getPassword());
            oldUser.setUsername(user.getUsername());
            oldUser.setEmail(user.getEmail());
            oldUser.setRoles(user.getRoles());

            User savedUser = userRepository.save(oldUser);
            return savedUser;

        } else {
            throw new RuntimeException("Can't find record with identifier: " + user.getId()+"or name "+user.getUsername());
        }
    }
    @Transactional
    public User createNewUser(User user) {

        if(user.getRoles() == null){

            Set<String> rolesnames= new HashSet<>();
            rolesnames.add("ROLE_GENERALUSER");
            return saveUserWithRoles(user, rolesnames);
        }
        else{

            return userRepository.save(user);
        }
    }
}