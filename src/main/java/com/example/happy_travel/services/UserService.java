package com.example.happy_travel.services;

import com.example.happy_travel.dtos.user.UserMapper;
import com.example.happy_travel.dtos.user.UserRequest;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.models.User;
import com.example.happy_travel.repositories.UserRepository;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.toDto(user)).toList();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserResponse addUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.username())){
        throw new RuntimeException("Username already exists, please choose another");
        }
        User newUser = UserMapper.toEntity(userRequest);
        User savedUser = userRepository.save(newUser);
        return UserMapper.toDto(savedUser);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id " + id));
        updatedUser.setUsername(userRequest.username());
        updatedUser.setEmail(userRequest.email());
        updatedUser.setPassword(userRequest.password());
        User newUser = userRepository.save(updatedUser);
        return UserMapper.toDto(newUser);
    }

    public void deleteUser(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
    }
}