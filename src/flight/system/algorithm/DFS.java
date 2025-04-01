package flight.system.algorithm;

import flight.system.data.Flight;
import flight.system.data.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFS {

    private static Map<String, List<Flight>> createGraph(List<Flight> flights) {
        Map<String, List<Flight>> graph = new HashMap<>();
        for (Flight flight : flights) {
            graph.putIfAbsent(flight.getFrom(), new ArrayList<>());
            graph.get(flight.getFrom()).add(flight);
        }
        return graph;
    }

    private static void findRoutes(Map<String, List<Flight>> graph, String current, String destination,
                                         List<String> path, List<Route> routes, double cost) {
        path.add(current);

        if (current.equals(destination)) {
            routes.add(new Route(path, cost));
        } else if (graph.containsKey(current)) {
            for (Flight flight : graph.get(current)) {
                if (!path.contains(flight.getTo())) {
                    findRoutes(graph, flight.getTo(), destination, path, routes, cost + flight.getPrice());
                }
            }
        }

        path.removeLast();
    }

    public static List<Route> findAllRoutes(List<Flight> flights, String origin, String destination) {
        Map<String, List<Flight>> graph = createGraph(flights);

        List<Route> routes = new ArrayList<>();
        findRoutes(graph, origin, destination, new ArrayList<>(), routes, 0);
        routes.sort(Comparator.comparingDouble(Route::getTotalPrice));
        return routes;
    }

}
