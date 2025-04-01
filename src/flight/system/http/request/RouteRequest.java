package flight.system.http.request;

public class RouteRequest {

    private final String origin;
    private final String destination;
    private final Integer maxFlights;

    public RouteRequest(String origin, String destination, Integer maxFlights) {
        this.origin = origin;
        this.destination = destination;
        this.maxFlights = maxFlights;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getMaxFlights() {
        return (maxFlights != null) ? maxFlights : Integer.MAX_VALUE;
    }

}
