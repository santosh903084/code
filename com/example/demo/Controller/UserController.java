package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.service.JwtService;
import com.example.demo.service.MyUserDetailService;


@RestController
public class UserController {

    @Autowired
    private MyUserDetailService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public void adduser(@RequestBody Users user) {
        service.adduser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = service.loadUserByUsername(user.getUsername());
            return jwtService.generateToken(userDetails);
        } else {
            return "Login Failed";
        }
    }
}
