package com.example.happy_travel.dtos.user;

import com.example.happy_travel.models.Role;
import com.example.happy_travel.models.User;

public class UserMapper {
    public static User toEntity(UserRequest userRequest){
        Role role = Role.USER;

        if (userRequest.role() != null && !userRequest.role().isEmpty()) {
            String roleStr = userRequest.role();

            if (roleStr.startsWith("ROLE_")) {
                roleStr = roleStr.substring(5);
            }

            try {
                role = Role.valueOf(roleStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                role = Role.USER;
            }
        }

        return new User(userRequest.username(), userRequest.email(), userRequest.password(), role);
    }

    public static UserResponse toDto(User user){
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole().getName());
    }
}