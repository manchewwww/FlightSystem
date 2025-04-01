import flight.system.data.Flight;
import flight.system.data.Route;
import flight.system.http.request.RouteRequestClient;
import flight.system.http.server.RoutesServer;
import flight.system.repositories.FlightRepository;

import java.util.List;

import static flight.system.algorithm.DFS.findAllRoutes;

public class Main {

    public static void main(String[] args) {
        List<Flight> flightsEx1 = FlightRepository.loadFlights("src/flight/system/files/flights_example_1.csv");
        List<Route> routesEx1 = findAllRoutes(flightsEx1, "SOF", "MLE");
        System.out.println("Example 1: ");
        routesEx1.forEach(r -> System.out.println(r.toString()));
        if (routesEx1.isEmpty()) {
            System.out.println("No routes found");
        }

        List<Flight> flightsEx2 = FlightRepository.loadFlights("src/flight/system/files/flights_example_2.csv");
        List<Route> routesEx2 = findAllRoutes(flightsEx2, "SOF", "MLE");
        System.out.println("Example 2: ");
        routesEx2.forEach(r -> System.out.println(r.toString()));
        if (routesEx2.isEmpty()) {
            System.out.println("No routes found");
        }

        List<Flight> flightsEx3 = FlightRepository.loadFlights("src/flight/system/files/flights_example_3.csv");
        List<Route> routesEx3 = findAllRoutes(flightsEx3, "SOF", "MLE");
        System.out.println("Example 3: ");
        routesEx3.forEach(r -> System.out.println(r.toString()));
        if (routesEx3.isEmpty()) {
            System.out.println("No routes found");
        }

        try {
            RoutesServer server = new RoutesServer();
            server.startServer();

            String json = "{ \"origin\": \"SOF\", \"destination\": \"MLE\" }";
            String json1 = "{ \"origin\": \"SOF\", \"destination\": \"MLE\", \"maxFlights\": \"1\" }";

            System.out.println(RouteRequestClient.getResponse(json));
            System.out.println(RouteRequestClient.getResponse(json1));
            server.stopServer();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
