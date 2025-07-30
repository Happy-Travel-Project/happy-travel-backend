package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.user.UserRequest;
import com.example.happy_travel.dtos.user.UserRequestByAdmin;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
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
    private UserRequest userRequest;
    private UserRequest userUpdatedRequest;
    private UserRequestByAdmin userRequestByAdmin;
    private UserResponse userResponse;
    private UserResponse userUpdatedResponse;
    private UserResponse user2Response;
    private Long userId;
    private UserRequest userBadRequest;

    @BeforeEach
    void setUp() {
        userId = 1L;
        userResponse = new UserResponse(1L, "Ana", "ana@gmail.com", "ROLE_USER");
        user2Response = new UserResponse(2L, "Paola", "paola@gmail.com", "ROLE_USER");
        userRequest = new UserRequest("Ana", "ana@gmail.com", "Password123@", "USER");
        userUpdatedRequest = new UserRequest("Ana Carina", "ana@gmail.com", "NewPass456@", "USER");
        userUpdatedResponse = new UserResponse(1L, "Ana Carina", "ana@gmail.com", "ROLE_USER");
        userRequestByAdmin = new UserRequestByAdmin("Ana Admin", "ana@gmail.com", "ADMIN");

        userResponses = new ArrayList<>();
        userResponses.add(userResponse);
        userResponses.add(user2Response);

        userBadRequest = new UserRequest(" ", "invalid-email", "", "USER");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllUsers_whenUsersExist_returnsListOfUsers() throws Exception {
        given(userService.getAllUsers()).willReturn(userResponses);

        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2))).andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].username").value("Ana")).andExpect(jsonPath("$[0].email").value("ana@gmail.com")).andExpect(jsonPath("$[0].role").value("ROLE_USER")).andExpect(jsonPath("$[1].id").value(2)).andExpect(jsonPath("$[1].username").value("Paola")).andExpect(jsonPath("$[1].email").value("paola@gmail.com")).andExpect(jsonPath("$[1].role").value("ROLE_USER"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getUserById_whenUserExists_returnsUser() throws Exception {
        given(userService.getUserResponseById(userId)).willReturn(userResponse);

        mockMvc.perform(get("/users/{id}", userId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.username").value("Ana")).andExpect(jsonPath("$.email").value("ana@gmail.com")).andExpect(jsonPath("$.role").value("ROLE_USER"));

        verify(userService, times(1)).getUserResponseById(userId);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void updateUser_whenValidRequest_returnsUpdatedUser() throws Exception {
        given(userService.updateUser(anyLong(), any(UserRequest.class))).willReturn(userUpdatedResponse);

        String json = objectMapper.writeValueAsString(userUpdatedRequest);

        mockMvc.perform(put("/users/{id}", userId).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.username").value("Ana Carina")).andExpect(jsonPath("$.email").value("ana@gmail.com")).andExpect(jsonPath("$.role").value("ROLE_USER"));

        verify(userService, times(1)).updateUser(eq(userId), any(UserRequest.class));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void updateUser_whenInvalidRequest_returnsBadRequest() throws Exception {
        String json = objectMapper.writeValueAsString(userBadRequest);

        mockMvc.perform(put("/users/{id}", userId).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());

        verify(userService, never()).updateUser(any(Long.class), any(UserRequest.class));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateUserByAdmin_whenValidRequest_returnsUpdatedUser() throws Exception {
        UserResponse adminUpdatedResponse = new UserResponse(1L, "Ana Admin", "ana@gmail.com", "ROLE_ADMIN");
        given(userService.updateUserByAdmin(anyLong(), any(UserRequestByAdmin.class))).willReturn(adminUpdatedResponse);

        String json = objectMapper.writeValueAsString(userRequestByAdmin);

        mockMvc.perform(put("/admin/users/{id}", userId).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.username").value("Ana Admin")).andExpect(jsonPath("$.email").value("ana@gmail.com")).andExpect(jsonPath("$.role").value("ROLE_ADMIN"));

        verify(userService, times(1)).updateUserByAdmin(eq(userId), any(UserRequestByAdmin.class));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteUser_whenValidId_returnsNoContent() throws Exception {
        doNothing().when(userService).deleteUser(userId);

        mockMvc.perform(delete("/users/{id}", userId)).andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(userId);
    }
}