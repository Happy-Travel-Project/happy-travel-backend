package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.models.Destination;
import com.example.happy_travel.models.User;
import com.example.happy_travel.security.CustomUserDetail;
import com.example.happy_travel.security.jwt.JwtService;
import com.example.happy_travel.services.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.happy_travel.models.Role;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebMvcTest(DestinationController.class)
@AutoConfigureMockMvc
@ActiveProfiles("tests")

public class DestinationControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private DestinationService destinationService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserResponse user1Response;
    private UserResponse user2Response;
    private User user1Entity;
    private User user2Entity;
    private List<DestinationResponse> destinationResponses;
    private DestinationResponse destination1Response;
    private DestinationResponse destination2Response;
    private DestinationRequest destination1Request;
    private Long destination1Id;
    private Destination destination1Entity;
    private Destination destination1EntityWithId;
    private Destination destination1EntityWithIdUpdated;
    private DestinationResponse destination1ExpectedResponse;
    private DestinationRequest destination1UpdatedRequest;
    private DestinationRequest destinationBadRequest;
    private Destination destination2Entity;
    private Long notExistentId;

    @BeforeEach
    void setUp() {
        destinationResponses = new ArrayList<>();

        user1Entity = new User("John Doe", "john@example.com", "Ghjsf$876", Role.USER);
        user2Entity = new User("Jane Smith", "jane@example.com", "Ghjsf$876", Role.USER);

        user1Response = new UserResponse(1L, "John Doe", "john@example.com", "ROLE_USER");
        user2Response = new UserResponse(2L, "Jane Smith", "jane@example.com", "ROLE_USER");

        destination1Response = new DestinationResponse(1L, "Eiffel Tower", "France", "Paris", "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg", "Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly the symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.", user1Response);
        destination2Response = new DestinationResponse(2L, "Statue of Liberty", "USA", "New York", "https://images.pexels.com/photos/290386/pexels-photo-290386.jpeg", "The Statue of Liberty is a colossal neoclassical sculpture on Liberty Island in New York Harbor, within New York City. The copper-clad statue, a gift to the United States from the people of France, was designed by French sculptor Frédéric Auguste Bartholdi and its metal framework was built by Gustave Eiffel.", user2Response);
        destination1Request = new DestinationRequest("Eiffel Tower", "France", "Paris", "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg", "Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly the symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.");
        destinationResponses.add(destination1Response);
        destinationResponses.add(destination2Response);
        destination1Id = 1L;
        notExistentId = 5L;
        destination1Entity = new Destination("A wonderful trip for those who love nature", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.", user1Entity);
        destination1EntityWithId = new Destination("A wonderful trip for those who love nature", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.", user1Entity);
        destination1EntityWithIdUpdated = new Destination("A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.", user2Entity);
        destination1ExpectedResponse = new DestinationResponse(1l, "A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.", user1Response);
        destination1UpdatedRequest = new DestinationRequest("A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destinationBadRequest = new DestinationRequest(" ", "a", "", "a", " ");
        destination2Entity = new Destination("The northern lights in Norway", "Norway", "Lofoten", "https://images.lge.com.sg/images/product-placeholder.png", "Our bold claim is that this part of Norway, with its multiple islands, deep fjords and steep mountains, is among the world's most beautiful and interesting places in which to see the northern lights.");
    }

    @Test
    @WithMockUser
    void getAllDestinations_whenDestinationsExist_returnsListOfDestinationsResponse() throws Exception {
        given(destinationService.getAllDestinations()).willReturn(destinationResponses);

        mockMvc.perform(get("/destinations")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].title", is("Eiffel Tower")))
                .andExpect(jsonPath("$[1].title", is("Statue of Liberty")))
                .andExpect(jsonPath("$[0].country", is("France")))
                .andExpect(jsonPath("$[1].country", is("USA")))
                .andExpect(jsonPath("$[0].city", is("Paris")))
                .andExpect(jsonPath("$[1].city", is("New York")))
                .andExpect(jsonPath("$[0].image", is("https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg")))
                .andExpect(jsonPath("$[1].image", is("https://images.pexels.com/photos/290386/pexels-photo-290386.jpeg")))
                .andExpect(jsonPath("$[0].description", is("Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly the symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.")))
                .andExpect(jsonPath("$[1].description", is("The Statue of Liberty is a colossal neoclassical sculpture on Liberty Island in New York Harbor, within New York City. The copper-clad statue, a gift to the United States from the people of France, was designed by French sculptor Frédéric Auguste Bartholdi and its metal framework was built by Gustave Eiffel.")));

        verify(destinationService, times(1)).getAllDestinations();
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void addDestination_whenCorrectRequest_returnsDestinationResponse() throws Exception {

        DestinationRequest request = new DestinationRequest("Eiffel Tower", "France", "Paris", "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg", "Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.");
        DestinationResponse response = new DestinationResponse(1L,"Eiffel Tower", "France", "Paris", "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg", "Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.", user1Response);

        given(destinationService.addDestination(Mockito.any(DestinationRequest.class),Mockito.any(User.class))).willReturn(response);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/destinations").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(user(new CustomUserDetail(user1Entity)))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Eiffel Tower"))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.city").value("Paris"))
                .andExpect(jsonPath("$.image").value("https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg"))
                .andExpect(jsonPath("$.description").value("Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it."));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void updateDestination_whenCorrectRequest_isOk() throws Exception {
        given(destinationService.updateDestination(anyLong(), any(DestinationRequest.class), any(User.class)))
                .willReturn(destination1ExpectedResponse);

        String json = objectMapper.writeValueAsString(destination1UpdatedRequest);

        mockMvc.perform(put("/destinations/{id}", destination1Id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(user(new CustomUserDetail(user1Entity)))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("A wonderful trip for those who love nature and animals"))
                .andExpect(jsonPath("$.country").value("Brazil"))
                .andExpect(jsonPath("$.city").value("Ilha Grande"))
                .andExpect(jsonPath("$.image").value("https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png"))
                .andExpect(jsonPath("$.description").value("Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone."));

        verify(destinationService, times(1)).updateDestination(anyLong(), any(DestinationRequest.class), any(User.class));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void updateDestination_whenBadRequest_returnsBadRequest() throws Exception {
        String json = objectMapper.writeValueAsString(destinationBadRequest);

        mockMvc.perform(put("/destinations/{id}", destination1Id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(user(new CustomUserDetail(user1Entity)))
                        .with(csrf()))
                .andExpect(status().isBadRequest());

        verify(destinationService, never()).updateDestination(anyLong(), any(DestinationRequest.class), any(User.class));
    }

    @Test
    void deleteDestination_whenIdExists_returnsNoContent() throws Exception {
        doNothing().when(destinationService).deleteDestination(destination1Id, user1Entity);
        mockMvc.perform(delete("/destinations/{id}", destination1Id)
                        .with(user(new CustomUserDetail(user1Entity)))
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}