package com.example.happy_travel.dtos.user;

import com.example.happy_travel.models.Role;
import com.example.happy_travel.models.User;

public class UserMapper {
    public static User toEntity(UserRequest userRequest){
        Role role = Role.USER;

        if (userRequest.role() != null && !userRequest.role().isEmpty()) {
            String roleName = userRequest.role();

            if (roleName.startsWith("ROLE_")) {
                roleName = roleName.substring(5);
            }

            try {
                role = Role.valueOf(roleName.toUpperCase());
            } catch (IllegalArgumentException exception) {
                role = Role.USER;
            }
        }

        return new User(userRequest.username(), userRequest.email(), userRequest.password(), role);
    }

    public static User toEntityFromAdmin(UserRequestByAdmin userRequestByAdmin, User existingUser) {
        Role role = Role.USER;
        if (userRequestByAdmin.role() != null && !userRequestByAdmin.role().isEmpty()) {
            String roleName = userRequestByAdmin.role();
            if (roleName.startsWith("ROLE_")) {
                roleName = roleName.substring(5);
            }
            try {
                role = Role.valueOf(roleName.toUpperCase());
            } catch (IllegalArgumentException exception) {
                role = Role.USER;
            }
        }
        existingUser.setUsername(userRequestByAdmin.username());
        existingUser.setEmail(userRequestByAdmin.email());
        existingUser.setRole(role);
        return existingUser;
    }


    public static UserResponse toDto(User user){
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole().getName());
    }
}