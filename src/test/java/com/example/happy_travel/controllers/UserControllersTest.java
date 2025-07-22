package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.user.UserRequest;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.models.User;
import com.example.happy_travel.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;




@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tests")

public class UserControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<UserResponse> userResponses;
    private UserResponse user1Response;
    private UserResponse user2Response;
    private UserRequest user1Request;
    private Long user1Id;
    private User user1Entity;

    @BeforeEach
    void setUp() {
        userResponses = new ArrayList<>();
        user1Response = new UserResponse(1L, "Ana", "ana@gmail.com");
        user2Response = new UserResponse(2L, "Paola", "paola@gmail.com");
        user1Request  = new UserRequest("Ana", "ana@gmail.com", "2yU#2yU#");
        userResponses.add(user1Response);
        userResponses.add(user2Response);
        user1Id = 1L;
        user1Entity = new User("Ana", "ana@gmail.com","2yU#2yU#");
    }

    @Test
    void getAllUsers_whenUsersExist_returnsListOfUsersResponse() throws Exception {
        given(userService.getAllUsers()).willReturn(userResponses);

        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].username").value("Ana"))
                .andExpect(jsonPath("$[1].username").value("Paola"))
                .andExpect(jsonPath("$[0].email").value("ana@gmail.com"))
                .andExpect(jsonPath("$[1].email").value("paola@gmail.com"));
    }

    @Test
    void getUserById_whenUserExists_returnsUser() throws Exception {
                given(userService.getUserResponseById(user1Id)).willReturn(user1Response);

        mockMvc.perform(get("/api/users/{id}",user1Id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Ana"))
                .andExpect(jsonPath("$.email").value("ana@gmail.com"));
    }

    @Test
    void addUser_whenCorrectRequest_returnsUserResponse() throws Exception {

        UserRequest request = new UserRequest("Ana","ana@gmail.com", "2yU#2yU#");
        UserResponse response = new UserResponse(1L,"Ana", "ana@gmail.com");

        given(userService.addUser(Mockito.any(UserRequest.class))).willReturn(response);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("Ana"))
                .andExpect(jsonPath("$.email").value("ana@gmail.com"));
    }

    // Add updateUser test

    @Test
    void deleteUser_whenIdExists_returnsNoContent() throws Exception{
        mockMvc.perform(delete("/api/users/{id}", user1Id))
                .andExpect(status().isNoContent());
    }
}