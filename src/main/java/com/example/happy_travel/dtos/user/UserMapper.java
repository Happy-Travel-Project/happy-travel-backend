package com.example.happy_travel.dtos.user;

import com.example.happy_travel.models.User;

public class UserMapper {
    public static User toEntity(UserRequest userRequest){
        return new User(userRequest.username(), userRequest.email(), userRequest.password());
    }

    public static  UserResponse toDto(User user){
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
