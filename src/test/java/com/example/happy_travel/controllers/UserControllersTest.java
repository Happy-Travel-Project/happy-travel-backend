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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private UserRequest user1Request;
    private UserRequest user1UpdatedRequest;
    private UserResponse user1Response;
    private UserResponse user1ExpectedResponse;
    private UserResponse user2Response;
    private Long user1Id;
    private User user1Entity;
    private UserRequest userBadRequest;

    @BeforeEach
    void setUp() {
        userResponses = new ArrayList<>();
        user1Response = new UserResponse(1L, "Ana", "ana@gmail.com");
        user2Response = new UserResponse(2L, "Paola", "paola@gmail.com");
        user1Request = new UserRequest("Ana", "ana@gmail.com", "2yU#2yU#");
        user1UpdatedRequest = new UserRequest("Ana Carina", "ana@gmail.com", "2yU#2yU#");
        user1ExpectedResponse = new UserResponse(1L, "Ana Carina", "ana@gmail.com");
        userResponses.add(user1Response);
        userResponses.add(user2Response);
        user1Id = 1L;
        user1Entity = new User("Ana", "ana@gmail.com", "2yU#2yU#");
        userBadRequest = new UserRequest(" ", "s", "");
    }

    @Test
    void getAllUsers_whenUsersExist_returnsListOfUsersResponse() throws Exception {
        given(userService.getAllUsers()).willReturn(userResponses);

        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2))).andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[1].id").value(2)).andExpect(jsonPath("$[0].username").value("Ana")).andExpect(jsonPath("$[1].username").value("Paola")).andExpect(jsonPath("$[0].email").value("ana@gmail.com")).andExpect(jsonPath("$[1].email").value("paola@gmail.com"));
    }

    @Test
    void getUserById_whenUserExists_returnsUser() throws Exception {
        given(userService.getUserResponseById(user1Id)).willReturn(user1Response);

        mockMvc.perform(get("/users/{id}", user1Id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.username").value("Ana")).andExpect(jsonPath("$.email").value("ana@gmail.com"));
    }

    @Test
    void addUser_whenCorrectRequest_returnsUserResponse() throws Exception {

        UserRequest request = new UserRequest("Ana", "ana@gmail.com", "2yU#2yU#");
        UserResponse response = new UserResponse(1L, "Ana", "ana@gmail.com");

        given(userService.addUser(Mockito.any(UserRequest.class))).willReturn(response);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.username").value("Ana")).andExpect(jsonPath("$.email").value("ana@gmail.com"));
    }

    @Test
    void updateUser_whenCorrectRequest_isOk() throws Exception {
        given(userService.updateUser(anyLong(), any(UserRequest.class))).willReturn(user1ExpectedResponse);

        String json = objectMapper.writeValueAsString(user1UpdatedRequest);

        mockMvc.perform(put("/users/{id}", user1Id).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.username").value("Ana Carina")).andExpect(jsonPath("$.email").value("ana@gmail.com"));
    }

    @Test
    void updateUser_whenBadRequest_returnsBadRequest() throws Exception {

        String json = objectMapper.writeValueAsString(userBadRequest);

        mockMvc.perform(put("/users/{id}", user1Id).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());

        verify(userService, never()).updateUser(any(Long.class), any(UserRequest.class));
    }

    @Test
    void deleteUser_whenIdExists_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/users/{id}", user1Id)).andExpect(status().isNoContent());
    }
}