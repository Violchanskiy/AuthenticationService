package org.example.controller;

import org.example.exception.UserAlreadyExistsException;
import org.example.jwt.JwtService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public SecurityController(
            UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(
            @RequestParam String username, @RequestParam String password) {
        try {
            userService.registrationUser(username, password);
            return ResponseEntity.ok("Success");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("User with this username already exists");
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(
            @RequestParam String username, @RequestParam String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            String jwt = jwtService.generateToken(username);
            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

}
