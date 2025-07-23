package com.example.happy_travel.services;

import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.models.Destination;
import com.example.happy_travel.repositories.DestinationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DestinationServicesTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationService destinationService;
    private Destination destination1Entity;
    private Destination destination2Entity;

    @BeforeEach
    void setup() {
        destination1Entity = new Destination("A wonderful trip for those who love nature", "Brazil", "Ilha Grande", "https://www.latamairlines.com/content/dam/latamxp/sites/vamos-latam/news-playas-brasil/destino/resize/V26_Brazil_IlhaGrande_VivaLATAM_NevinXavier1.png", "Ilha Grande – which literally translates to Big Island – is a tropical island just off the coast of Rio de Janeiro state and is about 160 kilometers from Copacabana. It is an island defined by its white beaches and clear blue waters. It is also a car-free zone.");
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

}