package flight.system.data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteTest {

    @Test
    void testValidRouteCreation() {
        List<String> routes = List.of("NYC", "CHI", "LAX");
        Route route = new Route(routes, 300.0);
        assertEquals(routes, route.getCities(), "Invalid set of cities list");
        assertEquals(300.0, route.getTotalPrice(),  "Invalid set of totalPrice");
    }

    @Test
    void testToStringTest() {
        Route route = new Route(List.of("NYC", "CHI", "LAX"), 300.0);
        assertEquals("NYC, CHI, LAX, 300.0", route.toString(), "toString method return different result from expected");
    }

}