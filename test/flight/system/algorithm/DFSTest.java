package flight.system.algorithm;

import flight.system.data.Flight;
import flight.system.data.Route;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DFSTest {

    @Test
    void testFindAllValidRoutes() {
        List<Flight> flights = List.of(
            new Flight("NYC", "CHI", 100.0),
            new Flight("CHI", "LAX", 200.0),
            new Flight("NYC", "LAX", 400.0)
        );

        List<Route> routes = DFS.findAllRoutes(flights, "NYC", "LAX");

        assertEquals(2, routes.size(), "Invalid routes found. Program expect 2 routes!");
        assertEquals(300.0, routes.get(0).getTotalPrice(), "First route price is calculated incorrect");
        assertEquals(400.0, routes.get(1).getTotalPrice(), "Second route price is calculated incorrect");
    }

    @Test
    void testFindAllWithSameOriginAndDestination() {
        List<Flight> flights = List.of(
            new Flight("NYC", "CHI", 100.0),
            new Flight("CHI", "LAX", 200.0),
            new Flight("NYC", "LAX", 400.0)
        );

        List<Route> routes = DFS.findAllRoutes(flights, "NYC", "NYC");

        assertEquals(1, routes.size(), "Invalid routes found. Program expect 2 routes!");
        assertEquals(0, routes.getFirst().getTotalPrice(), "First route price is calculated incorrect");
    }

    @Test
    void testWithNoValidRoute() {
        List<Flight> flights = List.of(
            new Flight("NYC", "CHI", 100.0)
        );

        List<Route> routes = DFS.findAllRoutes(flights, "NYC", "LAX");
        assertTrue(routes.isEmpty(), "When no one route founded. Routes from DFS should be empty!");
    }

}