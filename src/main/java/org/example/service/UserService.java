package org.example.service;

import org.example.exception.UserAlreadyExistsException;
import org.example.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    void registrationUser(String username, String password) throws UserAlreadyExistsException;


    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
