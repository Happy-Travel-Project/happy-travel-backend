package com.example.happy_travel.services;

import com.example.happy_travel.dtos.user.UserMapper;
import com.example.happy_travel.dtos.user.UserRequest;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.exceptions.EntityAlreadyExistsException;
import com.example.happy_travel.exceptions.EntityNotFoundException;
import com.example.happy_travel.models.User;
import com.example.happy_travel.repositories.UserRepository;
import com.example.happy_travel.security.CustomUserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.toDto(user)).toList();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName(), "Id", id.toString()));
    }

    public UserResponse getUserResponseById(Long id){
        User user = getUserById(id);
        return UserMapper.toDto(user);
    }

    public UserResponse addUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.username())){
        throw new EntityAlreadyExistsException(User.class.getSimpleName(), "username", userRequest.username());
        }
        User newUser = UserMapper.toEntity(userRequest);
        User savedUser = userRepository.save(newUser);
        return UserMapper.toDto(savedUser);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName(), "id",id.toString()));
        updatedUser.setUsername(userRequest.username());
        updatedUser.setEmail(userRequest.email());
        updatedUser.setPassword(userRequest.password());
        User newUser = userRepository.save(updatedUser);
        return UserMapper.toDto(newUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new EntityNotFoundException(User.class.getSimpleName(), "id", id.toString());
        }
        getUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new CustomUserDetail(user))
                .orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName(), "username", username));
    }
}