package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @BeforeEach
    void setUp() {
        userResponses = new ArrayList<>();
        UserResponse user1Response = new UserResponse(1L, "Ana", "ana@gmail.com");
        UserResponse user2Response = new UserResponse(2L, "Paola", "paola@gmail.com");

        userResponses.add(user1Response);
        userResponses.add(user2Response);
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
}