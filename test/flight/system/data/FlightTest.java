package flight.system.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FlightTest {

    @Test
    void testValidFlightCreation() {
        Flight flight = new Flight("NYC", "LAX", 150.0);
        assertEquals("NYC", flight.getFrom(), "Invalid set of from!");
        assertEquals("LAX", flight.getTo(), "Invalid set of to!");
        assertEquals(150.0, flight.getPrice(), "Invalid set of price!");
    }

    @Test
    void testInvalidOriginCityLength() {
        assertThrows(IllegalArgumentException.class, () -> new Flight("NY", "LAX", 150.0),
            "When from city length is different from 3 IllegalArgumentException should be thrown");
    }

    @Test
    void testInvalidDestinationCityLength() {
        assertThrows(IllegalArgumentException.class, () -> new Flight("SOF", "LX", 150.0),
            "When to city length is different from 3 IllegalArgumentException should be thrown");
    }

    @Test
    void testWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Flight("NYC", "LAX", -100.0),
            "When price is smaller or equal to 0 IllegalArgumentException should be thrown");
    }

}