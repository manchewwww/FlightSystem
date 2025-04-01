package flight.system.http.service;

import flight.system.algorithm.DFS;
import flight.system.data.Route;
import flight.system.repositories.FlightRepository;

import java.util.List;

public class FlightService {

    public List<Route> findRoutes(String path, String origin, String destination) {
        return DFS.findAllRoutes(FlightRepository.loadFlights(path), origin, destination);
    }

}