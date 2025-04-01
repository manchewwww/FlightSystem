package flight.system.http.service;

import flight.system.data.Route;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class FlightServiceTest {

    @Test
    void testFindRoutes() {
        FlightService serviceMock = Mockito.mock(FlightService.class);
        Mockito.when(serviceMock.findRoutes("srs/flight/system/flights_example_1.csv", "NYC", "LAX")).thenReturn(List.of(new Route(List.of("NYC", "LAX"), 150.0)));

        List<Route> routes = serviceMock.findRoutes("srs/flight/system/flights_example_1.csv", "NYC", "LAX");

        assertEquals(1, routes.size(), "Actual size of returned routes are different from expected");
        assertEquals(150.0, routes.getFirst().getTotalPrice(), "Actual price of returned route are different from expected");
    }
}