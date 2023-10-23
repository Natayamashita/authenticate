package com.test.Authenticate.Services;

import org.springframework.stereotype.Service;

import com.test.Authenticate.model.User;
import com.test.Authenticate.repositories.UserRepository;

@Service
public class UserService {
	
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        return user;
    }
    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }
    
}