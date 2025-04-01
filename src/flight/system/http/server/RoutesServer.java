package flight.system.http.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import flight.system.http.handler.FlightHandler;

public class RoutesServer {

    private static final int PORT = 8888;
    private HttpServer server;

    public void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/api/routes", new FlightHandler());
        server.start();
        System.out.println("Server started on port 8888");
    }

    public void stopServer() {
        server.stop(0);
    }

}