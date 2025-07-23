package com.example.happy_travel.services;

import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.exceptions.EntityNotFoundException;
import com.example.happy_travel.models.Destination;
import com.example.happy_travel.repositories.DestinationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DestinationServicesTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationService destinationService;
    private Destination destination1Entity;
    private Destination destination1EntityWithId;
    private Destination destination1EntityWithIdUpdated;
    private  DestinationResponse destination1ExpectedResponse;
    private DestinationRequest destination1UpdatedRequest;
    private Destination destination2Entity;
    private Long destination1Id;
    private Long notExistentId;

    @BeforeEach
    void setup() {
        destination1Id = 1L;
        notExistentId = 5L;
        destination1Entity = new Destination("A wonderful trip for those who love nature", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination1EntityWithId = new Destination(1L, "A wonderful trip for those who love nature", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination1EntityWithIdUpdated = new Destination(1L, "A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination1ExpectedResponse = new DestinationResponse(1L, "A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination1UpdatedRequest = new DestinationRequest("A wonderful trip for those who love nature and animals", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
        destination2Entity = new Destination("The northern lights in Norway", "Norway", "Lofoten", "https://images.lge.com.sg/images/product-placeholder.png", "Our bold claim is that this part of Norway, with its multiple islands, deep fjords and steep mountains, is among the world's most beautiful and interesting places in which to see the northern lights.");
    }

    @Test
    void getAllDestinations_whenDestinationsExist_returnsListOfDestinationsResponse() {
        when(destinationRepository.findAll()).thenReturn(List.of(destination1Entity, destination2Entity));

        List<DestinationResponse> result = destinationService.getAllDestinations();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("A wonderful trip for those who love nature",result.getFirst().title());
        assertEquals("Brazil", result.getFirst().country());
        assertEquals("Ilha Grande",result.getFirst().city());
        assertEquals("https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", result.getFirst().image());
        assertEquals("Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.", result.getFirst().description());
        assertEquals("The northern lights in Norway",result.get(1).title());
        assertEquals("Norway", result.get(1).country());
        assertEquals("Lofoten", result.get(1).city());
        assertEquals("https://images.lge.com.sg/images/product-placeholder.png", result.get(1).image());
        assertEquals("Our bold claim is that this part of Norway, with its multiple islands, deep fjords and steep mountains, is among the world's most beautiful and interesting places in which to see the northern lights.", result.get(1).description());

        verify(destinationRepository, times(1)).findAll();
    }

    @Test
    void updateDestination_whenDestinationExists_returnsDestinationResponse() {
        when(destinationRepository.findById(destination1Id)).thenReturn(Optional.of(destination1EntityWithId));
        when(destinationRepository.save(any(Destination.class))).thenReturn(destination1EntityWithIdUpdated);

        DestinationResponse result = destinationService.updateDestination(destination1Id, destination1UpdatedRequest);

        assertEquals(destination1ExpectedResponse, result);
        assertEquals("A wonderful trip for those who love nature and animals", result.title());
        assertEquals("Brazil", result.country());
        assertEquals("Ilha Grande", result.city());
        assertEquals("https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", result.image());
        assertEquals("Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.", result.description());

        verify(destinationRepository, times(1)).findById(destination1Id);
        verify(destinationRepository, times(1)).save(any(Destination.class));
    }

    @Test
    void updateDestination_whenDestinationDoesNotExists_returnsEntityNotFoundException(){
        when(destinationRepository.findById(notExistentId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> destinationService.updateDestination(notExistentId, destination1UpdatedRequest));

        assertEquals("Destination with id 5 was not found", exception.getMessage());

        verify(destinationRepository, times(1)).findById(notExistentId);
        verify(destinationRepository, never()).save(any(Destination.class));
    }

    @Test
    void deleteDestination_whenDestinationExists_returnsVoid(){
        when(destinationRepository.findById(eq(destination1Id))).thenReturn(Optional.of(destination1Entity));
        doNothing().when(destinationRepository).deleteById(destination1Id);

        destinationService.deleteDestination(destination1Id);

        verify(destinationRepository, times(1)).findById(destination1Id;
        verify(destinationRepository, times(1)).deleteById(destination1Id);

    }
}