package flight.system.http.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import flight.system.data.Route;
import flight.system.http.request.RouteRequest;
import flight.system.http.service.FlightService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

public class FlightHandler implements HttpHandler {

    private static final int ERROR_STATUS_CODE = 405;
    private static final int RESPONSE_LENGTH = -1;
    private static final int OK_STATUS_CODE = 200;
    private static final String UTF = "utf-8";
    private static final String FILE_PATH = "src/flight/system/files/flights_example_2.csv";

    private static final FlightService FLIGHT_SERVICE = new FlightService();
    private static final Gson GSON = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            try (InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), UTF)) {
                RouteRequest request = GSON.fromJson(isr, RouteRequest.class);
                List<Route> routes =
                    FLIGHT_SERVICE.findRoutes(FILE_PATH, request.getOrigin(), request.getDestination());
                routes =
                    routes.stream().filter(route -> route.getCities().size() - 1 <= request.getMaxFlights()).toList();
                String response = GSON.toJson(routes);

                exchange.sendResponseHeaders(OK_STATUS_CODE, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        } else {
            exchange.sendResponseHeaders(ERROR_STATUS_CODE, RESPONSE_LENGTH);
        }
    }

}