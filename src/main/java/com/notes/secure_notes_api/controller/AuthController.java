package com.notes.secure_notes_api.controller;

import com.notes.secure_notes_api.dto.LoginRequest;
import com.notes.secure_notes_api.dto.LoginResponse;
import com.notes.secure_notes_api.model.User;
import com.notes.secure_notes_api.service.AuthService;
import com.notes.secure_notes_api.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;



    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User savedUser = authService.register(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 1. Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // 2. If authentication is successful, generate a token
        String token = jwtService.generateToken(authentication.getName());

        // 3. Create a response object and send it back
        LoginResponse response = new LoginResponse(token);
        return ResponseEntity.ok(response);
    }



}
