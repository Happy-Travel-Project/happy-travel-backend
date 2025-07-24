package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.models.Destination;
import com.example.happy_travel.services.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(DestinationController.class)
@AutoConfigureMockMvc
@ActiveProfiles("tests")

public class DestinationControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DestinationService destinationService;

    @Autowired
    private ObjectMapper objectMapper;

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
        destination1Response = new DestinationResponse(1L, "Eiffel Tower", "France", "Paris", "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg", "Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.");
        destination2Response = new DestinationResponse(2L, "Statue of Liberty", "USA", "New York", "https://images.pexels.com/photos/290386/pexels-photo-290386.jpeg", "The Statue of Liberty is a colossal neoclassical sculpture on Liberty Island in New York Harbor, within New York City. The copper-clad statue, a gift to the United States from the people of France, was designed by French sculptor Frédéric Auguste Bartholdi and its metal framework was built by Gustave Eiffel.");
        destination1Request = new DestinationRequest("Eiffel Tower", "France", "Paris", "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg", "Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.");
        destinationResponses.add(destination1Response);
        destinationResponses.add(destination2Response);
        destination1Id = 1L;
        notExistentId = 5L;
        destination1Entity = new Destination("A wonderful trip for those who love nature", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination1EntityWithId = new Destination(1L, "A wonderful trip for those who love nature", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination1EntityWithIdUpdated = new Destination(1L, "A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination1ExpectedResponse = new DestinationResponse(1L, "A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination1UpdatedRequest = new DestinationRequest("A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destinationBadRequest = new DestinationRequest(" ", "a", "", "a", " ");
        destination2Entity = new Destination("The northern lights in Norway", "Norway", "Lofoten", "https://images.lge.com.sg/images/product-placeholder.png", "Our bold claim is that this part of Norway, with its multiple islands, deep fjords and steep mountains, is among the world's most beautiful and interesting places in which to see the northern lights.");
    }

    @Test
    void getAllDestinations_whenDestinationsExists_returnsListOfDestinationsResponse() throws Exception {
        given(destinationService.getAllDestinations()).willReturn(destinationResponses);

        mockMvc.perform(get("/destinations").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].title").value("Eiffel Tower"))
                .andExpect(jsonPath("$[1].title").value("Statue of Liberty"))
                .andExpect(jsonPath("$[0].country").value("France"))
                .andExpect(jsonPath("$[1].country").value("USA"))
                .andExpect(jsonPath("$[0].city").value("Paris"))
                .andExpect(jsonPath("$[1].city").value("New York"))
                .andExpect(jsonPath("$[0].image").value("https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg"))
                .andExpect(jsonPath("$[1].image").value("https://images.pexels.com/photos/290386/pexels-photo-290386.jpeg"))
                .andExpect(jsonPath("$[0].description").value("Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it."))
                .andExpect(jsonPath("$[1].description").value("The Statue of Liberty is a colossal neoclassical sculpture on Liberty Island in New York Harbor, within New York City. The copper-clad statue, a gift to the United States from the people of France, was designed by French sculptor Frédéric Auguste Bartholdi and its metal framework was built by Gustave Eiffel."));
    }

    @Test
    void addDestination_whenCorrectRequest_returnsDestinationResponse() throws Exception {

        DestinationRequest request = new DestinationRequest("Eiffel Tower", "France", "Paris", "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg", "Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.");
        DestinationResponse response = new DestinationResponse(1L,"Eiffel Tower", "France", "Paris", "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg", "Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it.");

        given(destinationService.addDestination(Mockito.any(DestinationRequest.class))).willReturn(response);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/destinations").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Eiffel Tower"))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.city").value("Paris"))
                .andExpect(jsonPath("$.image").value("https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg"))
                .andExpect(jsonPath("$.description").value("Built for the Universal Exhibition of 1889, the Eiffel Tower is undoubtedly ‘the’ symbol of Paris. At 330 metres high, it towers over the city from the Champ-de-Mars and visitors have marvelled at it for generations. With glittering lights at night and an ice rink in winter, it continues to innovate and amaze those who see it or climb it."));
    }

    @Test
    void updateDestination_whenCorrectRequest_isOk() throws Exception{
    given(destinationService.updateDestination(anyLong(), any(DestinationRequest.class))).willReturn(destination1ExpectedResponse);

    String json = objectMapper.writeValueAsString(destination1UpdatedRequest);

    mockMvc.perform(put("/destinations/{id}", destination1Id).contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("A wonderful trip for those who love nature and animals"))
            .andExpect(jsonPath("$.country").value("Brazil"))
            .andExpect(jsonPath("$.city").value("Ilha Grande"))
            .andExpect(jsonPath("$.image").value("https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png"))
            .andExpect(jsonPath("$.description").value("Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone."));
    }

    @Test
    void updateDestination_whenBadRequest_returnsBadRequest() throws Exception{

        String json = objectMapper.writeValueAsString(destinationBadRequest);

        mockMvc.perform(put("/destinations/{id}", destination1Id).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());

        verify(destinationService, never()).updateDestination(any(Long.class), any(DestinationRequest.class));
    }

    @Test
    void deleteDestination_whenIdExists_returnsNoContent() throws Exception{
        mockMvc.perform(delete("/destinations/{id}", destination1Id))
                .andExpect(status().isNoContent());
    }
}
