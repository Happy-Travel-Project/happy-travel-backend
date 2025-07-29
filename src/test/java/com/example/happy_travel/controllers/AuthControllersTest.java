package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.user.UserRequest;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.models.Role;
import com.example.happy_travel.models.User;
import com.example.happy_travel.security.CustomUserDetail;
import com.example.happy_travel.security.jwt.JwtService;
import com.example.happy_travel.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tests")
public class AuthControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequest userRequest;
    private UserResponse userResponse;
    private final String jwtToken = "jwt.test.token";

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest("Ana", "ana@gmail.com", "2yU#2yU#", "USER");
        userResponse = new UserResponse(1L, "Ana", "ana@gmail.com", "ROLE_USER");
    }

    @Test
    void register_WhenValidRequest_ReturnsCreatedUser() throws Exception {
        given(userService.addUser(any(UserRequest.class))).willReturn(userResponse);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userResponse.id()))
                .andExpect(jsonPath("$.username").value(userResponse.username()))
                .andExpect(jsonPath("$.email").value(userResponse.email()))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }

    @Test
    void login_WhenValidCredentials_ReturnsJwtToken() throws Exception {
        CustomUserDetail userDetails = new CustomUserDetail(
                new User("Ana", "ana@gmail.com", "password", Role.USER)
        );
        Authentication authentication = Mockito.mock(Authentication.class);

        given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .willReturn(authentication);
        given(authentication.getPrincipal()).willReturn(userDetails);
        given(jwtService.generateToken(userDetails)).willReturn(jwtToken);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(jwtToken));
    }
}
