package com.godstimeProjects2023.librarySystem.service;

import com.godstimeProjects2023.librarySystem.entity.User;
import com.godstimeProjects2023.librarySystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User addNewUser(User user) {
        user.setPassword( user.getPassword());
        user.setLastModifiedDate( new Date() );
        user.setCreatedDate( user.getCreatedDate() );

        user.setActive(1);
        return userRepository.addNew(user);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.getUserValidate(username, password);
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public User registerUser(User user) {
        return userRepository.registerUser(user);
    }
}

