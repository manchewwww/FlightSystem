package flight.system.http.request;

import flight.system.http.server.RoutesServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouteRequestClientTest {

    private static RoutesServer server;

    @BeforeAll
    static void setup() throws Exception {
        server = new RoutesServer();
        server.startServer();
    }

    @AfterAll
    static void teardown() {
        server.stopServer();
    }

    @Test
    void testValidRequestWithMaxFlightsTwo() {
        String requestJson = "{\"origin\":\"SOF\", \"destination\":\"MLE\", \"maxFlights\":\"2\"}";
        String response = RouteRequestClient.getResponse(requestJson);
        String actualResponse = "[{\"cities\":[\"SOF\",\"LON\",\"MLE\"],\"totalPrice\":30.0},{\"cities\":[\"SOF\",\"MLE\"],\"totalPrice\":70.0}]";

        assertEquals(response, actualResponse, "Response from server did not match the expected response when maxFlights is 2");
    }

    @Test
    void testValidRequestWithMaxFlightsOne() {
        String requestJson = "{\"origin\":\"SOF\", \"destination\":\"MLE\", \"maxFlights\":\"1\"}";
        String response = RouteRequestClient.getResponse(requestJson);
        String actualResponse = "[{\"cities\":[\"SOF\",\"MLE\"],\"totalPrice\":70.0}]";

        assertEquals(response, actualResponse, "Response from server did not match the expected response when maxFlights is 1");
    }

    @Test
    void testValidRequestWithoutMaxFlights() {
        String requestJson = "{\"origin\":\"SOF\", \"destination\":\"MLE\"}";
        String response = RouteRequestClient.getResponse(requestJson);
        String actualResponse = "[{\"cities\":[\"SOF\",\"LON\",\"MLE\"],\"totalPrice\":30.0},{\"cities\":[\"SOF\",\"MLE\"],\"totalPrice\":70.0}]";

        assertEquals(response, actualResponse, "Response from server did not match the expected response without maxFlights");
    }

    @Test
    void testInvalidRequest() {
        String requestJson = "{\"origin\":\"NYC\",\"destination\":\"INVALID\",\"maxFlights\":1}";
        String response = RouteRequestClient.getResponse(requestJson);

        assertTrue(response.contains("[]"), "An empty response should be returned when no valid routes are found.");
    }

}